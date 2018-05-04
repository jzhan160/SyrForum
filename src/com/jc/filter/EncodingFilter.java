////////////////////////////////////////////////////////////////////
// EncodingFilter.java   deal with different encoding function    //
// ver 1.0                                                        //
// Author: Jiacheng Zhang                                                               //
////////////////////////////////////////////////////////////////////
/*
 * This package provides one Java class EncodingFilter which implement
 * the filer interface. This class mainly deal with different encoding
 * function
 *
 *
 * Maintenance history
 * Version 1.0
 * 05/04/2018
 *
 * */

package com.jc.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String charEncoding=null;

    //----------------< destructor for this class >-------------------------------
    public void destroy() {
    }

    //----------------< filer message flow >--------------------------------------
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (!charEncoding.equals(req.getCharacterEncoding())) {
            req.setCharacterEncoding(charEncoding);
        }
        resp.setCharacterEncoding(charEncoding);
        chain.doFilter(req, resp);
    }

    //----------------< initial function >----------------------------------------
    public void init(FilterConfig config) throws ServletException {
        charEncoding=config.getInitParameter("encoding");
        if (charEncoding==null) {
            throw new ServletException("No encode！");
        }
    }

}
