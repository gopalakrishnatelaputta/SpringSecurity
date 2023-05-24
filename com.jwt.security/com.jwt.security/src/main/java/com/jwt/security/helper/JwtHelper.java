package com.jwt.security.helper;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static javax.crypto.Cipher.SECRET_KEY;

public class JwtHelper
{
    private static final long serialVersionID=-2550105162607488L;

    public static final long JWT_TOKEN_VALIDITY=5*68*60;

    private String secret ="GopalaKrishna996344";

    public String extractUsername(String token)
    {
        return extractClaim(token, io.jsonwebtoken.Claims::getSubject);
    }
    public Date extractExpiration(String token)
    {
        return extractClaim(token,io.jsonwebtoken.Claims::getExpiration);
    }

    public <T> T extractClaim(String token,Function<Claims,T>claimResolver)
    {
        final io.jsonwebtoken.Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private io.jsonwebtoken.Claims extractAllClaims(String token)
    {

        return (Claims) Jwts.parser().setSigningKey(String.valueOf(SECRET_KEY)).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username)
    {

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(SignatureAlgorithm.HS256, String.valueOf(SECRET_KEY)).compact();
    }
    public Boolean validateToken(String token,UserDetails userDetails)
    {
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())&&isTokenExpired(token));
    }

}
