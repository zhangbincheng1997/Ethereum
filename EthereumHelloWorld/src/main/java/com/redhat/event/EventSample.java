package com.redhat.event;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.web3j.abi.EventValues;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

/**
 * @author littleredhat
 */
public class EventSample {
	// 钱包密码
	private static String password = "123456";
	// 钱包路径
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// 对方地址
	private static String toAddress = "0x6c079ADe6a7F4C74EEBD85C272d2B3930f1224a4";
	// TransferContract智能合约地址
	private static String contractAddress = "0x911e84aceBD45B7858F9a5E876B94a2d984c3E01";
	// Gas价格
	private static int gasPrice = 1200000;
	// Gas上限
	private static int gasLimit = 2000000;
	// contract instantiation
	private static EventContract contract;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(password, path);

		// Solidity contract type abstraction for interacting with smart contracts via native Java types.
		contract = new EventContract(contractAddress, web3j, credentials, BigInteger.valueOf(gasPrice),
				BigInteger.valueOf(gasLimit));

		CompletableFuture<TransactionReceipt> future = contract.transferFunc(credentials.getAddress(), toAddress, 520);
		System.out.println("send!!!");
		TransactionReceipt transferReceipt = future.get();
		System.out.println("Transfer completed with tx hash: " + transferReceipt.getTransactionHash());

		EventValues v = contract.processTransferEvent(transferReceipt);
		for (int i = 0; i < v.getIndexedValues().size(); ++i) {
			System.out.println(v.getIndexedValues().get(i).toString());
		}
		for (int i = 0; i < v.getNonIndexedValues().size(); ++i) {
			System.out.println(v.getNonIndexedValues().get(i).getValue());
		}
	}
}
