package com.redhat.helloworld.contract;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import com.redhat.helloworld.util.Consts;

/**
 * @author littleredhat
 */
public class HelloWorldMain {

	public static void main(String[] args) throws Exception {
		// 获取凭证
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		System.out.println("getCredentialsAddress : " + credentials.getAddress());

		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		// 获取合约
		HelloWorldContract contract = new HelloWorldContract(Consts.HELLOWORLD_ADDR, web3j, credentials,
				Consts.GAS_PRICE, Consts.GAS_LIMIT);
		System.out.println("getContractAddress : " + contract.getContractAddress());

		// set
		Future<TransactionReceipt> transferReceipt = contract.set(10000);
		System.out.println("waiting...");
		System.out.println("set : " + transferReceipt.get().getTransactionHash());

		// get
		Future<Uint256> result = contract.get();
		System.out.println("waiting...");
		System.out.println("get : " + result.get().getValue().intValue());
	}
}
