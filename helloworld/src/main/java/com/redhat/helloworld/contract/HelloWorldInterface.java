package com.redhat.helloworld.contract;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface HelloWorldInterface {

	// get
	public Future<Uint256> get();

	// set
	public Future<TransactionReceipt> set(int x);
}
