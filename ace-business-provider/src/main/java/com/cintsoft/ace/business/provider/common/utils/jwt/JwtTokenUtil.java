package com.cintsoft.ace.business.provider.common.utils.jwt;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.Serializable;
import java.util.Date;

@Data
@RefreshScope
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil<T> implements Serializable {

    private final String PAYLOAD_KEY = "PAYLOAD_KEY";

    private String secret;

    private Long expiration;

    /**
     * 从数据声明生成令牌
     *
     * @param payload 数据声明
     * @return 令牌
     */
    public String generateToken(T payload) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 1000L);
        return Jwts.builder()
                .claim(PAYLOAD_KEY, JSONUtil.toJsonStr(payload))
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public T getPayloadFromToken(String token, Class<T> clazz) {
        final Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return JSONUtil.toBean((String) body.get(PAYLOAD_KEY), clazz);
    }
}
