package com.abolkog.springboot.tut.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 10/10/2018 10:37 PM.
 */
public class AuthFilter extends OncePerRequestFilter
{

    @Value("${auth.header}")
    private String TOKEN_HEADER;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * ** Check if user are Authorized
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
        //1.Get token from header
        //2.Make sure token are valid
        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null) {
            
            String token = header.substring("Bearer ".length());
            String username = tokenUtil.getUserNameFromToken(token);
            
            // make sure token are valid
            if (username != null) {
                
                UserDetails userDetails = userService.loadUserByUsername(username);
                
                if (tokenUtil.isTokenValid(token, userDetails)) {
                    
                    UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
