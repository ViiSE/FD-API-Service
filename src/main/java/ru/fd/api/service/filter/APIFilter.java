/*
 *  Copyright 2019 ViiSE.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
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
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Enumeration<String> headerNames = (request).getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                if(name.equalsIgnoreCase("authorization")) {
                    try {
                        String token = (request).getHeader(name).replaceFirst("Bearer ", "");
                        Claims claims = Jwts.parser()
                                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                                .parseClaimsJws(token).getBody();
                        if(claims.getId().equals(jwtId)                &&
                                claims.getIssuer().equals(jwtIssuer)   &&
                                claims.getSubject().equals(jwtSubject)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    } catch (ExpiredJwtException |
                            UnsupportedJwtException |
                            MalformedJwtException |
                            SignatureException |
                            IllegalArgumentException ex) {
                        logger.error(APIFilter.class, ex.getMessage() + " <CAUSE>: " + ex.getCause());
                    }
                }
            }
        } else
            logger.error(APIFilter.class, "Http header is null");
    }
}
