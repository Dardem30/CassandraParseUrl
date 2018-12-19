package com.example.gradlecassandraparseurl.service;

import com.example.gradlecassandraparseurl.dao.LinkDAO;
import com.example.gradlecassandraparseurl.model.Link;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParseUrlService {
    private final LinkDAO linkDAO;

    /**
     * This method get all advertisements href of page
     *
     * @param url input url
     * @return string(all url of advertisements)
     */
    public final List<String> parseForAdvertisements(final String url) throws IOException {
        try {
            final Document doc = Jsoup.connect(url).get();
            final String host = URI.create(url).getHost();
            final Elements links = doc.select("a[href]");
            return links.stream()
                    .map(link -> link.attr("href"))
                    .filter(hrefUrl -> hrefUrl.contains("http") && !host.equals(URI.create(hrefUrl).getHost()))
                    .peek(hrefUrl -> linkDAO.save(new Link(hrefUrl, url)))
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            log.error("Error during parsing url for advertisements", e);
            throw new IOException(e);
        }
    }

    /**
     * This method get all href(with type) of page
     *
     * @param url input url
     * @return string(all url of advertisements)
     */
    public final Map parseForType(final String url) throws IOException {
        try {
            final Document doc = Jsoup.connect(url).get();
            final Elements links = doc.select("a[href]");
            return links.stream()
                    .map(Element::children)
                    .collect(Collectors.toMap(key -> key.stream().anyMatch(chil -> chil.tag().getName().equals("img")) ? "images" : "others", value -> {
                        final List<Element> list = value.stream().map(Element::parent).collect(Collectors.toList());
                        return list.size() != 0 ? list.get(0).attr("href") : "miss";
                    }, (k1, k2) -> k1 + "," + k2));
        } catch (final IOException e) {
            log.error("Error during parsing url for type", e);
            throw new IOException(e);
        }
    }

    public final Map<String, Set> getAllAdvertisementsLinkedImages(final String url) throws IOException {
        try {
            final Document doc = Jsoup.connect(url).get();
            final Elements links = doc.select("a");
            final String host = URI.create(url).getHost();
            final Set<Element> linked = links.stream()
                    .filter(link -> link.children().stream().anyMatch(chil -> chil.tag().getName().equals("img")) && link.children().attr("src").contains("http")
                            && link.attr("href").contains("http") && !host.equals(URI.create(link.attr("href")).getHost()))
                    .collect(Collectors.toSet());
            final Map<String, Set> res = new HashMap<>();
            res.put("links", linked.stream().map(link -> link.attr("href")).collect(Collectors.toSet()));
            res.put("images", linked.stream().map(Element::children).map(link -> link.attr("src")).collect(Collectors.toSet()));
            return res;
        } catch (final IOException e) {
            log.error("Error during parsing url for advertisements", e);
            throw new IOException(e);
        }
    }

    public final boolean isImage(final String url) throws IOException {
        final URL imUrl = new URL(url);
        final BufferedImage bufferedImage = ImageIO.read(imUrl);
        return bufferedImage != null && new ImageIcon(bufferedImage).getImage().getWidth(null) != -1;
    }

    public final Iterable<Link> parseHistory() {
        return linkDAO.findAll();
    }
}