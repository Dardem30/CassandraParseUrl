package com.example.gradlecassandraparseurl.service;

import com.example.gradlecassandraparseurl.bo.HrefModel;
import com.example.gradlecassandraparseurl.da.HrefModelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class ParseUrlService {
    private final HrefModelRepository hrefModelRepository;
    /**
     * This method get all advertisements href of page
     * @param inUrl
     * @return string(all url of advertisements)
     */
    public final String parseUrl(final String inUrl) throws IOException {
        try {
            final Document doc = Jsoup.connect(inUrl).get();
            final String host = URI.create(inUrl).getHost();
            final Elements links = doc.select("a[href]");
            return "Рекламные ссылки: \n    " + links.stream()
                    .map(link -> link.attr("href"))
                    .parallel()
                    .filter(hrefUrl -> hrefUrl.contains("http") && !host.equals(URI.create(hrefUrl).getHost()))
                   // .peek(hrefUrl -> hrefModelRepository.save(new HrefModel(hrefUrl)))
                    .collect(Collectors.joining("\n    "));
        } catch (final IOException e) {
            log.error("Error during parsing url", e);
            throw new IOException(e);
        }
    }
}