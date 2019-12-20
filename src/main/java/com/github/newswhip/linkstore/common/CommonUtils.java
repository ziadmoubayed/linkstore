package com.github.newswhip.linkstore.common;


import java.net.URI;

/**
 * Utility class for urls
 */
public class CommonUtils {

    /**
     * Hiding constructor
     */
    CommonUtils() {
    }

    /**
     * Returns the domain from the url
     *
     * @param urlString string url
     * @return domain name without http:// and www.
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
