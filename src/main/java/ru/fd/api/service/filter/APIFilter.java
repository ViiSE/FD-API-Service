/*
 *  Copyright 2019 FD Company. All rights reserved.
 *
 *  Licensed under the FD License.
 *
 *  To read the license text, please contact: viise@outlook.com
 *
 *  Author: ViiSE.
 */

package ru.fd.api.service.filter;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.fd.api.service.log.LoggerService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class APIFilter implements Filter {

    @Value("${fd.api.service.jwt-id}")
    private String jwtId;

    @Value("${fd.api.service.jwt-issuer}")
    private String jwtIssuer;

    @Value("${fd.api.service.jwt-secret}")
    private String jwtSecret;

    @Value("${fd.api.service.jwt-subject}")
    private String jwtSubject;

    @Autowired
    private LoggerService logger;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
        // Убираем из фильтра URL swagger'а
        if(url.contains("swagger-ui.html") ||
                url.contains("webjars/springfox-swagger-ui/") ||
                url.contains("swagger-resources") ||
                url.contains("csrf") ||
                url.contains("api-docs") ||
                url.contains("docs"))
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            Enumeration<String> headerNames = (request).getHeaderNames();

            // Есть ли среди списка хедеров хедер authorization?
            boolean isAuthHeader = false;

            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if (name.equalsIgnoreCase("authorization")) {
                        isAuthHeader = true;
                        try {
                            String token = (request).getHeader(name).replaceFirst("Bearer ", "");
                            Claims claims = Jwts.parser()
                                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                                    .parseClaimsJws(token).getBody();
                            if (claims.getId().equals(jwtId) &&
                                    claims.getIssuer().equals(jwtIssuer) &&
                                    claims.getSubject().equals(jwtSubject)) {
                                filterChain.doFilter(servletRequest, servletResponse);
                            } else
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        } catch (ExpiredJwtException |
                                UnsupportedJwtException |
                                MalformedJwtException |
                                SignatureException |
                                IllegalArgumentException ex) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            logger.error(APIFilter.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
                        }
                    }
                }

                // Header authorization отсутствует
                if(!isAuthHeader)
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                logger.error(APIFilter.class, "Http header is null");
            }
        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
