package main.java.utility;

public class LogUtility {
    public static void LOG(String key, Object... args) {
        System.out.println(String.format(key, args));
    }
}
