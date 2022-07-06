package com.pw.util;

import lombok.var;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {
    public static void main(String[] args) {
        var password = "ua123";
        System.out.println("password: " + password);
        System.out.println("password Encriptado: " + encriptarPassword(password));

    }
    public static String encriptarPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}