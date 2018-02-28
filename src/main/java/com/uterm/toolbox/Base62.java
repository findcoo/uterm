package com.uterm.toolbox;

import java.util.HashMap;

/**
 * Base62
 * 
 * number encoding and decoding system. based ASCII 62 characters(A-Za-z0-9)
 */
public class Base62 {
    private static final char[] BASE62_CODE = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789".toCharArray();
    private static final HashMap<Character, Integer> BASE62_INDEX = new HashMap<Character, Integer>();
    static {
        for (int i=0; i<BASE62_CODE.length; i++) {
            BASE62_INDEX.put(BASE62_CODE[i], i);
        }
    }

    public static String encode(Long num) {
        String encoded = "";

        while (num > 0) {
            int remainder = (int)(num % 62);
            encoded += BASE62_CODE[remainder];
            num /= 62;
        }
        return encoded;
    }

    public static Long decode(String code) {
        long decoded = 0;
        long pow = 1;

        for (int i=0; i<code.length(); i++) {
            int digit = BASE62_INDEX.get(code.charAt(i));
            decoded += digit * pow;
            pow *= 62;
        }

        return decoded;
    }
}