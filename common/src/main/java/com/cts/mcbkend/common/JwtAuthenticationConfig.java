package com.cts.mcbkend.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Config JWT.
 * Only one property 'sukanta.security.jwt.secret' is mandatory.
 *
 * @author sukanta 2017/10/18
 */
@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${sukanta.security.jwt.url:/login}")
    private String url;

    @Value("${sukanta.security.jwt.header:Authorization}")
    private String header;

    @Value("${sukanta.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${sukanta.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${sukanta.security.jwt.secret}")
    private String secret;
}