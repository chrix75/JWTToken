package com.imscd.poc.security;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.imscd.poc.exceptions.JWTTokenException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by csperandio on 15/07/2016.
 */
public class BasicJWTManager implements JWTManager {
    private final String issuer = "https://services.imscd.com";
    private final String secret = "992dbe8e-4f58-4edd-b9ed-946f9f9671ae";


    @Override
    public Map<String, String> buildToken(String apiKey, String login) {
        final long iat = System.currentTimeMillis() / 1000l; // issued at claim
        final long exp = iat + 10 * 3600L; // expires claim. In this case the token expires in 10 hours

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("aud", apiKey);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put("sub", login);

        final String jwt = signer.sign(claims);

        return newToken(jwt);
    }

    @Override
    public Map<String, String> buildFullTimeToken(String apiKey, String login) {
        final long iat = System.currentTimeMillis() / 1000l; // issued at claim

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("aud", apiKey);
        claims.put("iat", iat);
        claims.put("sub", login);

        final String jwt = signer.sign(claims);

        return newToken(jwt);
    }

    private Map<String,String> newToken(String jwt) {
        Map<String, String> token = new HashMap<>();
        token.put("token", jwt);

        return token;
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String,Object> claims= verifier.verify(token);
            return true;
        } catch (JWTVerifyException| NoSuchAlgorithmException | IOException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, Object> getClaims(String token) throws JWTTokenException {
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String,Object> claims= verifier.verify(token);
            return claims;
        } catch (JWTVerifyException | NoSuchAlgorithmException | IOException | SignatureException | InvalidKeyException e) {
            throw new JWTTokenException(e);
        }
    }

}
