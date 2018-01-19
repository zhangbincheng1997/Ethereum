package com.redhat.helloworld.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author littleredhat
 */
public class Consts {

    private static Properties p;

    // 初始化配置
    static {
        p = new Properties();
        InputStream in = Consts.class.getResourceAsStream("/config.properties");
        InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
        try {
            p.load(r);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GAS价格
    public static BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    // GAS上限
    public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000L);

    // 钱包密码
    public static String PASSWORD = p.getProperty("password");
    // 钱包路径
    public static String PATH = p.getProperty("path");
    // 钱包目录
    public static String DIRECTORY = p.getProperty("directory");
    // 合约地址
    public static String HELLOWORLD_ADDR = p.getProperty("helloworldAddr");
}