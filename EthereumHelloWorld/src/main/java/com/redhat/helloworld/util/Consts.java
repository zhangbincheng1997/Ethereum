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

	// 获取数据
	public static String getData(String key) {
		if (p == null) {
			try {
				p = readProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p.getProperty(key);
	}

	// 读取配置
	private static Properties readProperties() throws IOException {
		Properties p = new Properties();
		InputStream in = Consts.class.getResourceAsStream("/config.properties");
		InputStreamReader r = new InputStreamReader(in, Charset.forName("UTF-8"));
		p.load(r);
		in.close();
		return p;
	}

	// Gas价格
	public static BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
	// Gas上限
	public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000L);
	// ETHER以太币
	public static BigInteger ETHER = new BigInteger("1000000000000000000");
	// 钱包密码
	public static String PASSWORD = getData("password");
	// 钱包路径
	public static String PATH = getData("path");
	// 钱包目录
	public static String DIRECTORY = getData("directory");
	// HelloWorld智能合约地址
	public static String HELLOWORLD_ADDR = "0x5a4dc569C7B395130c58A9B0C183fEf6c4957AA9";
}
