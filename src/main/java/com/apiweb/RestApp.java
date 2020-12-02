package com.apiweb;
import com.apiweb.controller.GenerateId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class RestApp
{
    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(RestApp.class, args);
    }

}


