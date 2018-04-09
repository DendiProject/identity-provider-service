/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dendiproject.identityproviderservice;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.time.Instant;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 *
 * @author ArtemShevelyukhin
 */
@Service
public class JWTHandler {

    public String createJWT(String issuer, String subject, String id, String email, String key) {

        JwtBuilder builder = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(300)))
                .claim("id", id)
                .claim("email", email)
                .signWith(SignatureAlgorithm.HS512, key);

        return builder.compact();
    }

    public void parseJWT(String jwt, String key) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
