package com.redhat.helloworld.util;

import java.io.IOException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.redhat.helloworld.contract.HelloWorldContract;

/**
 * @author littleredhat
 */
public class Util {

	/**
	 * 获取管理员凭证
	 * 
	 * @param contractAddress
	 *            合约地址
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials() {
		// 管理员凭证
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * 返回众筹合约
	 * 
	 * @param credentials
	 *            钱包凭证
	 * @param contractAddress
	 *            合约地址
	 * @return
	 * @throws Exception
	 */
	public static HelloWorldContract GetHelloWorldContract(Credentials credentials, String contractAddress) {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		// 获取合约
		HelloWorldContract contract = new HelloWorldContract(contractAddress, web3j, credentials, Consts.GAS_PRICE,
				Consts.GAS_LIMIT);
		return contract;
	}
}
