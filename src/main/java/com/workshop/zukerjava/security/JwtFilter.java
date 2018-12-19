package com.workshop.zukerjava.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.workshop.zukerjava.security.JwtUtils.TOKEN_PARAM_NAME;

/**
 * Add urlPatterns if other urls need to be filtered.
 */
@Component
@WebFilter(filterName = "jwt_filter", urlPatterns = {"/usercenter/*"})
public class JwtFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("JwtFilter start");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String jwtToken = servletRequest.getParameter(TOKEN_PARAM_NAME);
        if (jwtToken == null) {
            toResponse(servletResponse, 400, TOKEN_PARAM_NAME + " not found.");
            return;
        }
        String userId = JwtUtils.verifyAndGetUserId(jwtToken);
        if (userId == null) {
            toResponse(servletResponse, 400, "Varify " + TOKEN_PARAM_NAME + " failed.");
            return;
        }
        ParamHttpServletRequestWrapper pr = new ParamHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        pr.addParameter("user_id", userId);
        filterChain.doFilter(pr, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("JwtFilter end");
    }

    private static void toResponse(ServletResponse response, int status, String msg) throws IOException {
        if (msg == null) {
            ((HttpServletResponse) response).sendError(status);
        } else {
            ((HttpServletResponse) response).sendError(status, msg);
        }
    }
}
