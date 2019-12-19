package com.github.newswhip.linkstore.common;


import java.net.URI;

/**
 * Utility class for urls
 */
public class UrlUtils {


    /**
     * Hiding constructor
     */
    UrlUtils() {
    }

    /**
     * Returns the domain from the url
     *
     * @param urlString
     * @return
     */
    public static String getDomain(String urlString) {
        if (!(urlString.startsWith("http://") || urlString.startsWith("https://"))) {
            urlString = "http://" + urlString;
        }
        URI uri = URI.create(urlString);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
