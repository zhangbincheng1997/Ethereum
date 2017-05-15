package com.redhat.contract;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

/**
 * @author littleredhat
 */
public class HelloWorldSample {
	// 钱包密码
	private static String password = "123456";
	// 钱包路径
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// HelloWorld智能合约地址
	private static String toAddress = "0x5a4dc569C7B395130c58A9B0C183fEf6c4957AA9";
	// Gas价格
	private static int gasPrice = 1200000;
	// Gas上限
	private static int gasLimit = 2000000;
	// contract instantiation
	private static HelloWorldContract contract;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(password, path);

		// Solidity contract type abstraction for interacting with smart contracts via native Java types.
		contract = new HelloWorldContract(toAddress, web3j, credentials, BigInteger.valueOf(gasPrice),
				BigInteger.valueOf(gasLimit));

		// set
		Future<TransactionReceipt> transferReceipt = contract.set(10000);
		System.out.println("set : " + transferReceipt.get().getTransactionHash());

		// get
		Future<Uint256> result = contract.get();
		System.out.println("get : " + result.get().getValue().intValue());
	}
}
