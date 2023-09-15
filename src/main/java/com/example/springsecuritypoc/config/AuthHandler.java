//package com.example.springsecuritypoc.config;
//
//import com.kastkode.springsandwich.filter.api.BeforeHandler;
//import com.kastkode.springsandwich.filter.api.Flow;
//import lombok.AllArgsConstructor;
//import org.jose4j.jwt.JwtClaims;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//
//
//@Component
////@AllArgsConstructor
//public class AuthHandler implements BeforeHandler {
//
//    Logger logger = LoggerFactory.getLogger(AuthHandler.class);
//
//    @Autowired
//    JWTBuilder jwtbuilder;
//
////    public AuthHandler(JWTBuilder jwtbuilder) {
////        this.jwtbuilder = jwtbuilder;
////    }
//
//
//    @Override
//    public Flow handle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, HandlerMethod handler, String[] flags) throws Exception {
//        logger.debug(request.getMethod() + "request is executing on" + request.getRequestURI());
//        String token = request.getHeader("Authorization");
//
//        if( token == null) {
//            throw new RuntimeException("Auth token is required");
//        }
//
//        JwtClaims claims = jwtbuilder.generateParseToken(token);
//        request.setAttribute("userId", claims.getClaimValue("userId").toString());
//        //do the db call and check is the user is still valid, avoided to make tutorial simple.
//        return Flow.CONTINUE;
//    }
//
//}
