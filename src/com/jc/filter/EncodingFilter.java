package com.jc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String charEncoding=null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (!charEncoding.equals(req.getCharacterEncoding())) {
            req.setCharacterEncoding(charEncoding);
        }
        resp.setCharacterEncoding(charEncoding);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        charEncoding=config.getInitParameter("encoding");
        if (charEncoding==null) {
            throw new ServletException("No encode！");
        }
    }

}
