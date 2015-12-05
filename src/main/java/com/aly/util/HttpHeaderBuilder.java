package com.aly.util;

import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Util to build http headers.
 */
public class HttpHeaderBuilder {
    public static void location(HttpServletRequest request, HttpServletResponse response, Integer id) {
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, id);
        response.setHeader("Location", uri.toASCIIString());
    }
}
