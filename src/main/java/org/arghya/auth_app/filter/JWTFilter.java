package org.arghya.auth_app.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.arghya.auth_app.model.User;
import org.arghya.auth_app.service.JWTService;
import org.arghya.auth_app.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("api/auth") || request.getServletPath().contains("h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;
        if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            jwt = authHeader.substring(7);
            userName = jwtService.extractUserName(jwt);
            if(Objects.nonNull(userName) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails user = userService.loadUserByUsername(userName);
                if(jwtService.isTokenValid(jwt, user)) {
                    UsernamePasswordAuthenticationToken authToken
                            = new UsernamePasswordAuthenticationToken(user,
                            null,
                            user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception){
            throw exception;
        }
    }
}
