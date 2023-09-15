package com.example.springsecuritypoc.config;

import com.example.springsecuritypoc.CustomUserDetailService;
import com.example.springsecuritypoc.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

//    @Value("${jwt.secret}")
//    private String secret;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        //final String authorizationHeader = request.getHeader("Authorization");
        final String authorizationHeader = request.getHeader(AUTHORIZATION);

        String username = null;
        final String jwt;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

//        jwt = authorizationHeader.substring(7);
//
//        username = jwtUtil.extractUsername(jwt);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.getClaimFromToken(jwt, Claims::getSubject);
                //username = jwtUtil.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                // Handle token expiration if needed
                // You can return a specific response or log the exception
            }
            //username = jwtUtil.getClaimFromToken(jwt, Claims::getSubject);
        }

        if (username != null) {
            // Perform any additional authorization checks here if needed
            // For example, you can validate user roles or permissions
            UserDetail userDetail = userDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetail))

            // If the token is valid and the user is authorized, you can proceed with the request
            filterChain.doFilter(request, response);
        } else {
            // If the token is invalid or missing, you can return an error response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return;
        }
    }

//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            if (jwtUtil.validateToken(jwt)) {
//                // Create an Authentication object and set it in the security context
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }

//        filterChain.doFilter(request, response);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//    }

