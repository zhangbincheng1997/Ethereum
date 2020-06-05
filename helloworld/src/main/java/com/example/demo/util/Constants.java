package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

    private static final Properties p;

    static {
        p = new Properties();
        InputStream in = Constants.class.getResourceAsStream("/application.properties");
        try {
            p.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String URL = p.getProperty("web3j.url");
    public static String PASSWORD = p.getProperty("credentials.password");
    public static String SOURCE = p.getProperty("credentials.source");
    public static String ADDRESS = p.getProperty("contract.address");
}