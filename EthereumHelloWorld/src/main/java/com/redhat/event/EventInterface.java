package com.redhat.event;

import java.util.concurrent.CompletableFuture;

import org.web3j.abi.EventValues;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface EventInterface {

	/**
	 * 转账事务
	 * 
	 * @param from
	 *            从
	 * @param to
	 *            到
	 * @param value
	 *            价值
	 * @return
	 */
	public CompletableFuture<TransactionReceipt> transferFunc(String from, String to, int value);

	/**
	 * 处理事件
	 * 
	 * @param transactionReceipt
	 * @return
	 */
	public EventValues processTransferEvent(TransactionReceipt transactionReceipt);
}
