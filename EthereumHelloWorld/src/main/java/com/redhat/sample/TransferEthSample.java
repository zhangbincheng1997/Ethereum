package com.redhat.sample;

import java.math.BigDecimal;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

/**
 * @author littleredhat
 * @description 转账事务
 */
public class TransferEthSample {
	// 钱包密码
	private static String password = "123456";
	// 钱包路径
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// 对方地址
	private static String toAddress = "0x6c079ade6a7f4c74eebd85c272d2b3930f1224a4";
	//
	private static double value = 100;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3 = Web3j.build(new HttpService());

		// To send Ether to another party using your Ethereum wallet file
		Credentials credentials = WalletUtils.loadCredentials(password, path);
		Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER);
	}
}
