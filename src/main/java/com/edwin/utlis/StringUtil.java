package com.edwin.utlis;

import java.util.Random;

/**
 * Create 6-digit verification code for user's forget password function
 */
public class StringUtil {

    public static String giveSixRandom() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
