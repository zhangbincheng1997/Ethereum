package com.redhat.contract;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import com.redhat.util.Consts;

/**
 * @author littleredhat
 */
public class HelloWorldSample {
	// contract instantiation
	private static HelloWorldContract contract;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

		// Solidity contract type abstraction for interacting with smart contracts via native Java types.
		contract = new HelloWorldContract(Consts.HELLOWORLD_CONTRACT_ADDRESS, web3j, credentials,
				BigInteger.valueOf(Consts.GAS_PRICE), BigInteger.valueOf(Consts.GAS_LIMIT));

		// set
		Future<TransactionReceipt> transferReceipt = contract.set(10000);
		System.out.println("set : " + transferReceipt.get().getTransactionHash());

		// get
		Future<Uint256> result = contract.get();
		System.out.println("get : " + result.get().getValue().intValue());
	}
}
