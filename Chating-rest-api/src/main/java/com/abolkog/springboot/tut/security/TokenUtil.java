package com.abolkog.springboot.tut.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 10/10/2018 10:15 PM.
 */
@Component
public class TokenUtil
{

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * reference: https://jwt.io/#debugger-io
     */
    private final String CLAIMS_SUBJECT = "sub";

    private final String CLAIMS_CREATED = "created";

    @Value("${auth.expiration}")
    private Long TOKEN_VALIDITY;// = 2592000L;

    @Value("${auth.secret}")
    private String TOKEN_SECRET;

    public String generateToken(UserDetails userDetails)
    {

        /**
         * claims
         * expiration
         * signWith
         * compact
         */
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put(CLAIMS_CREATED, new Date());

        String token = Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET).compact();
        
        log.info("token: " + token);
        
        return token;
    }

    public String getUserNameFromToken(String token)
    {
        try {
            Claims claims = getClaims(token);

            return claims.getSubject();
            
        } catch (Exception ex) {
            return null;
        }
    }

    private Date generateExpirationDate()
    {
        Date dateOfExpiration = new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
        
//        log.info("Expiration Date: "+ dateOfExpiration.toGMTString());
//        log.info("Expiration Date: " + dateOfExpiration.toString());
        log.info("Expiration Date: " +  dateOfExpiration.toLocaleString());
        
        return dateOfExpiration; // measured in milliseconds
    }

    public boolean isTokenValid(String token, UserDetails userDetails)
    {
        String username = getUserNameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token)
    {
        Date expiration = getClaims(token).getExpiration();
        boolean isExpire = expiration.before(new Date());
        return isExpire;
    }

    private Claims getClaims(String token)
    {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            claims = null;
        }

        return claims;
    }
}
