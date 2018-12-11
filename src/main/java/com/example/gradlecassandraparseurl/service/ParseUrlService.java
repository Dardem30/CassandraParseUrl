package com.example.gradlecassandraparseurl.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ParseUrlService {
    /**
     * This method get all advertisements href of page
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
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            log.error("Error during parsing url for advertisements", e);
            throw new IOException(e);
        }
    }
    /**
     * This method get all href(with type) of page
     * @param url input url
     * @return string(all url of advertisements)
     */
    public final Map<String, List<String>> parseForType(final String url) throws IOException {
        try {
            final Map<String, List<String>> result = new HashMap<>();
            final Document doc = Jsoup.connect(url).get();
            final Elements links = doc.select("a[href]");
            result.put("images",  links.stream()
                    .filter(link -> link.children().stream().anyMatch(chil -> chil.tag().getName().equals("img")))
                    .map(link -> link.attr("href"))
                    .collect(Collectors.toList()));
            result.put("others", links.stream()
                    .map(link -> link.attr("href"))
                    .filter(hrefUrl -> result.get("images").stream().noneMatch(imageUrl -> imageUrl.equals(hrefUrl)))
                    .collect(Collectors.toList()));
            return result;
        } catch (final IOException e) {
            log.error("Error during parsing url for type", e);
            throw new IOException(e);
        }
    }
    public final boolean isImage(final String url) throws IOException {
        final URL imUrl = new URL(url);
        final BufferedImage bufferedImage = ImageIO.read(imUrl);
        return bufferedImage != null && new ImageIcon(bufferedImage).getImage().getWidth(null) != -1;
    }
}