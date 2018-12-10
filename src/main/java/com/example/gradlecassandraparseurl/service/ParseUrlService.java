package com.example.gradlecassandraparseurl.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
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
                    .filter(link -> link.getElementsByTag("img") != null)
                    .map(link -> link.attr("href"))
                    .collect(Collectors.toList()));
            result.put("others", links.stream()
                    .filter(link -> link.getElementsByTag("img") == null)
                    .map(link -> link.attr("href"))
                    .collect(Collectors.toList()));
            return result;
        } catch (final IOException e) {
            log.error("Error during parsing url for type", e);
            throw new IOException(e);
        }
    }
}