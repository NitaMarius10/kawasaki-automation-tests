package utils;

import java.util.ResourceBundle;

public class Config {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");

    public static String get(String key) {
        return bundle.getString(key);
    }
}
