package utils;

import java.util.Properties;

public class Helper {
    Properties prop = new Properties();

    public long generateTimestamp() {
        return System.currentTimeMillis() / 60000;
    }
}