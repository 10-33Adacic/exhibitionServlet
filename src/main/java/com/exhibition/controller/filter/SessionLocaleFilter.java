package com.exhibition.controller.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionLocaleFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request,
                         HttpServletResponse response,
                         FilterChain chain
    ) throws IOException, ServletException {

        if (request.getParameter("sessionLocale") != null) {
            request.getSession()
                    .setAttribute("lang",
                            request.getParameter("sessionLocale"));
        }
        chain.doFilter(request, response);
    }
}
