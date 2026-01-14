package com.ajogious.backend.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service
public class PaystackSignatureService {

    @Value("${paystack.secret-key}")
    private String secretKey;

    @SneakyThrows
    public boolean isValidSignature(String payload, String signature) {
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(
                secretKey.getBytes(), "HmacSHA512");

        mac.init(keySpec);

        byte[] hash = mac.doFinal(payload.getBytes());
        String computed = bytesToHex(hash);

        return computed.equals(signature);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
