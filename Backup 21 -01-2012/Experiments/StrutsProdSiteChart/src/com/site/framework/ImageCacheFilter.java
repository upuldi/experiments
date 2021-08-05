package com.site.framework;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 11/19/11
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageCacheFilter implements Filter {

    private static Logger log = Logger.getLogger(ImageCacheFilter.class);


    private FilterConfig filterConfig;

    private Map<String, String> headersMap;

    public void init(FilterConfig filterConfig) throws ServletException {

        log.debug("start adding cache headers ......");

        this.filterConfig = filterConfig;

        String headerParam = filterConfig.getInitParameter("header");
        if (headerParam == null) {
            log.warn("No headers were found in the web.xml (init-param) for the HeaderFilter !");
            return;
        }

        // Init the header list :
        headersMap = new LinkedHashMap<String, String>();

        if (headerParam.contains("|")) {
            String[] headers = headerParam.split("|");
            for (String header : headers) {
                parseHeader(header);
            }

        } else {
            parseHeader(headerParam);
        }

        // Log configured headers .
        if (log.isInfoEnabled()) {
            log.info("The following headers were registered in the HeaderFilter :");
            Set<Map.Entry<String, String>> headers = headersMap.entrySet();
            for (Map.Entry<String, String> item : headers) {
                log.info(item.getKey() + ':' + item.getValue());
            }
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (headersMap != null) {
            // Add the header to the response
            Set<Map.Entry<String, String>> headers = headersMap.entrySet();
            for (Map.Entry<String, String> header : headers) {
                ((HttpServletResponse) response).setHeader(header.getKey(), header.getValue());
            }
        }
        // Continue
        chain.doFilter(request, response);
    }

    public void destroy() {

        this.filterConfig = null;
        this.headersMap = null;
    }

    private void parseHeader(String header) {
        String headerName = header.substring(0, header.indexOf(":"));
        if (!headersMap.containsKey(headerName)) {
            headersMap.put(headerName, header.substring(header.indexOf(":") + 1));
            headersMap.put("Cache-Control", "max-age");

        }
    }
}
