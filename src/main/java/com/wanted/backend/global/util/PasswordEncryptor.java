package com.wanted.backend.global.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {

    private final int SALT_LENGTH = 16; // Length of the salt in bytes

    public String hashPassword(String password) {
        // Generate a random salt
        final String salt = "passwordMaker";

        // Combine password and salt
        String saltedPassword = password + salt;

        // Hash the combined value using SHA-256
        byte[] hashedBytes = DigestUtils.sha256(saltedPassword);

        // Convert the hashed bytes to a hexadecimal string
        return Hex.encodeHexString(hashedBytes);
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return password.equals(hashedPassword);
    }
}
