package UTILS;

import java.nio.charset.StandardCharsets;

public class BYTES {
    public static String convertString(byte[] bytes)
    {
        String str = new String(bytes);
        return str;
    }

    public static String convertStringUTF8(byte[] bytes)
    {
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    public static byte[] toBytesUTF8(String str)
    {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return bytes;
    }

    public static byte[] toBytes(String str)
    {
        byte[] bytes = str.getBytes();
        return bytes;
    }
}
