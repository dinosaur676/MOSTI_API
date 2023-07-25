package com.example.mosti_api.mosti.application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalletUtil {
    static public String randomTag() {
        int length = 8;
        String randomHex = "0123456789abcdef";
        Random random = new Random();

        String output = "";

        for(int i = 0; i < length; ++i) {
            output += randomHex.charAt(random.nextInt(randomHex.length()));
        }

        return output;
    }
}
