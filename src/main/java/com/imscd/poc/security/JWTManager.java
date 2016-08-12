package com.imscd.poc.security;

import com.imscd.poc.exceptions.JWTTokenException;

import java.util.Map;

/**
 * Created by csperandio on 15/07/2016.
 */
public interface JWTManager {
    Map<String, String> buildToken(String apiKey, String login);

    boolean isValidToken(String token);

    Map<String, Object> getClaims(String token)  throws JWTTokenException;
}
