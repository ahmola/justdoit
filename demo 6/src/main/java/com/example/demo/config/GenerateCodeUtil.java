package com.example.demo.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {

    public static String generateCode(){
        String code;

        try {
            SecureRandom random =
                    SecureRandom.getInstanceStrong();

            int c = random.nextInt(9000)+1000;

            code = String.valueOf(c);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Problem when generating the random code.\n" + e.getMessage().toString());
        }

        return code;
    }
}
