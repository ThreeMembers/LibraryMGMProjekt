package sample.others;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Cryptography {
    public static String encode(String input){
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }
    public static String decode(String input){
        byte[] decodeByte = Base64.getDecoder().decode(input);
        return new String(decodeByte, StandardCharsets.UTF_8);
    }
}
