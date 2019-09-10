package com.github.mrcaoyc.office.online.factory.service.impl;

import com.github.mrcaoyc.office.online.factory.autoconfigurer.JwtTokenProperties;
import com.github.mrcaoyc.office.online.factory.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CaoYongCheng
 */
@Service
public class JwtServiceImpl implements JwtService {
    private final JwtTokenProperties jwtTokenProperties;

    public JwtServiceImpl(JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public String generateKey() {
        Map<String, Object> payload = new HashMap<>(0);
        return generateToken(payload);
    }

    /**
     * 创建jwt
     *
     * @param payload jwt中载荷
     * @return 令牌
     */
    private String generateToken(Map<String, Object> payload) {
        String secret = jwtTokenProperties.getSecret();
        SignatureAlgorithm algorithm = SignatureAlgorithm.forName(jwtTokenProperties.getAlgorithm());
        long now = System.currentTimeMillis();
        Date expTime = new Date(now + 1000 * jwtTokenProperties.getExpiresTime());
        return jwtTokenProperties.getType() + " " + Jwts.builder()
                .setClaims(payload)
                .setExpiration(expTime)
                .signWith(algorithm, secret)
                .compact();
    }
}
