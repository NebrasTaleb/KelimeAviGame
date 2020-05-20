package utils;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class GameUtils {
    
    public static CharsetEncoder encoder = Charset.forName("US-ASCII").newEncoder();
    public static CharsetDecoder decoder = Charset.forName("US-ASCII").newDecoder();
}
