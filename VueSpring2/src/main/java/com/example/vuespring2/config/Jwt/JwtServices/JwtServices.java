package com.example.vuespring2.config.Jwt.JwtServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServices {
    private static final  String Secret_Key="566D597133743677397A24432646294A404D635166546A576E5A723475377821";
    public String exstractUsername(String token){
        return  exstractClaim(token,Claims::getSubject);
    }
    public <T> T exstractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims =exstractAllClaims(token);
        return  claimResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(
            Map<String,Object>exstraClaim,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(exstraClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenIsValid(String token,UserDetails userDetails){
        final String username=exstractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenIsExpired(token));
    }
    private boolean isTokenIsExpired(String token){
        return exstractExpirtion(token).before(new Date());
    }

    private Date exstractExpirtion(String token) {
        return exstractClaim(token,Claims::getExpiration);
    }

    private Claims exstractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
