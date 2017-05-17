package com.redhat.contract;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.redhat.util.Consts;
import com.redhat.util.Util;

/**
 * @author littleredhat
 */
public class HelloWorldSample {
	// contract instantiation
	private static HelloWorldContract contract;

	public static void main(String[] args) throws Exception {
		// 获取管理员凭证
		Credentials credentials = Util.GetCredentials();
		if (credentials == null)
			System.exit(0);
		// 获取合约
		contract = Util.GetHelloWorldContract(credentials, Consts.HELLOWORLD_CONTRACT_ADDRESS);
		System.out.println("contractAddress : " + contract.getContractAddress());

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
