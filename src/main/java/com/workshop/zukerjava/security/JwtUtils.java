package com.workshop.zukerjava.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    public static final String TOKEN_PARAM_NAME = "token";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Authorization";

    private static final String SECRET = "zuker_secret";
    private static final String ISSUER = "issuer";

    private static final long DEFAULT_EXPIRATION = 3600L;   // 1hour

    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    public static String createToken(String user_id) {
        return createToken(user_id, DEFAULT_EXPIRATION);
    }

    public static String createToken(String user_id, long expiration) {
        return Jwts.builder()
                   .signWith(ALGORITHM, SECRET)
                   .setIssuer(ISSUER)
                   .setSubject(user_id)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                   .compact();
    }

    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                   .setSigningKey(SECRET)
                   .parseClaimsJws(token)
                   .getBody();
    }

    public static String verifyAndGetUserId(String token) {
        return "";
    }
}
