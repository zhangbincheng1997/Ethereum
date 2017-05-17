package com.redhat.helloworld.test;

import java.math.BigDecimal;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import com.redhat.helloworld.util.Consts;

/**
 * @author littleredhat
 */
public class TransferEthTest {
	// 对方地址
	private static String toAddress = "0x6c079ade6a7f4c74eebd85c272d2b3930f1224a4";
	// 金币数量
	private static double value = 100;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3 = Web3j.build(new HttpService());

		// To send Ether to another party using your Ethereum wallet file
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(value), Convert.Unit.ETHER);
	}
}
