package com.pmark.ticketingtool.utility;

import java.security.MessageDigest;

public class Tools {
    /**
     * generates a hash string from a given string with a given maximum length
     *
     * @param input  String to generate the hash from (if null empty String will be used to generate hash)
     * @param length max length of the hash code
     * @return a hash code that will NOT be NULL and lesser or equal in length than the given length
     */
    public static String generateHash(String input, int length) {
        //System.out.println("Generating hash from: ["+input+"]");
        String source = input == null ? "" : input;
        String res = "";
        byte[] hashedBytes = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            hashedBytes = digest.digest(source.getBytes("UTF-8"));
        } catch (Exception ex) {
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < hashedBytes.length; i++) {
            stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        res = stringBuffer.toString();
        res = res.substring(0, Math.min(res.length(), 128));
        return res;
    }
}
