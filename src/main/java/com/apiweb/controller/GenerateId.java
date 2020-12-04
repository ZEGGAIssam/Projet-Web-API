package com.apiweb.controller;

import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class GenerateId {

    static public String generateID()  {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(String.valueOf(LocalDateTime.now()).getBytes(StandardCharsets.UTF_8));
            String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(hashbytes);
            return sha3Hex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
}
