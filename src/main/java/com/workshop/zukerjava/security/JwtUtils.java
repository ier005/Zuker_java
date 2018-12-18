package com.workshop.zukerjava.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Component
public class JwtUtils implements CommandLineRunner {
    public static final boolean TEST_MODE = false;

    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    public static final String TOKEN_PARAM_NAME = "token";

    private static final String ISSUER = "issuer";

    private static final long DEFAULT_EXPIRATION = 6 * 3600L;   // 6hours

    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    private static final byte[] key = new byte[1024];


    public static void generateKey(long seed) {
        log.info("generate key");
        Random random = new Random(seed);
        random.nextBytes(key);
    }

    public static String createToken(String user_id) {
        return createToken(user_id, DEFAULT_EXPIRATION);
    }

    public static String createToken(String user_id, long expiration) {
        return Jwts.builder()
                   .signWith(ALGORITHM, Base64.getEncoder().encode(key))
                   .setIssuer(ISSUER)
                   .setSubject(user_id)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                   .compact();
    }

    public static String getUserId(String token) {
        Claims claims = getTokenBody(token);
        if (claims == null) {
            return null;
        }
        return claims.getSubject();
    }

    public static boolean isExpiration(String token) {
        Claims claims = getTokenBody(token);
        if (claims == null) {
            return true;
        }
        return claims.getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                       .setSigningKey(Base64.getEncoder().encode(key))
                       .parseClaimsJws(token)
                       .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean verify(String token) {
        return getTokenBody(token) != null;
    }

    public static String verifyAndGetUserId(String token) {
        if (TEST_MODE) {
            return "tony";
        }
        if (!verify(token)) {
            return null;
        }
        if (isExpiration(token)) {
            return null;
        }
        return getUserId(token);
    }

    @Override
    public void run(String... args) throws Exception {
        generateKey(new Date().getTime());
    }
}
