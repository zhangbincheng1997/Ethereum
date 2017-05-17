package com.redhat.helloworld.sample;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface HelloWorldInterface {

	/**
	 * 获取
	 * 
	 * @return
	 */
	public Future<Uint256> get();

	/**
	 * 设置
	 * 
	 * @param x
	 *            数据
	 * @return
	 */
	public Future<TransactionReceipt> set(int x);
}
