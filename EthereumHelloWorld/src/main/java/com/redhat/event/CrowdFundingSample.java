package com.redhat.event;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import com.redhat.util.Consts;

/**
 * @author littleredhat
 */
public class CrowdFundingSample {
	// private static String contractAddress = "0x......";
	private static CrowdFundingContract contract;
	private static String beneficiary = "0x6c079ADe6a7F4C74EEBD85C272d2B3930f1224a4";

	public static void main(String[] args) throws Exception {
		Web3j web3j = Web3j.build(new HttpService());
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		// contract = new CrowdFundingContract(contractAddress, web3j,
		// credentials, BigInteger.valueOf(Consts.GAS_PRICE),
		// BigInteger.valueOf(Consts.GAS_LIMIT));

		// 部署合约
		Future<CrowdFundingContract> future = CrowdFundingContract.deploy(web3j, credentials,
				BigInteger.valueOf(Consts.GAS_PRICE), BigInteger.valueOf(Consts.GAS_LIMIT), BigInteger.valueOf(0),
				new Address(beneficiary));
		System.out.println("deploying...");

		// 异步等待
		contract = future.get();
		System.out.println(contract.getContractAddress());

		// 发送金币
		TransactionReceipt transactionReceipt = contract.fund(BigInteger.valueOf(100).multiply(Consts.ETHER));
		System.out.println(transactionReceipt.getTransactionHash());

		// 接收金币
		Future<TransactionReceipt> future2 = contract.get();
		System.out.println("waiting...");

		// 异步等待
		TransactionReceipt transactionReceipt2 = future2.get();
		System.out.println(transactionReceipt2.getTransactionHash());

		// 众筹结束事件
		EventValues c = contract.processCrowdEndEvent(transactionReceipt2);
		if (!c.getNonIndexedValues().isEmpty()) {
			String beneficiary = c.getNonIndexedValues().get(0).getValue().toString();
			int amount = new BigInteger(c.getNonIndexedValues().get(1).getValue().toString()).divide(Consts.ETHER)
					.intValue();
			System.out.println("beneficiary == " + beneficiary);
			System.out.println("amount == " + amount);
		}
	}

}
