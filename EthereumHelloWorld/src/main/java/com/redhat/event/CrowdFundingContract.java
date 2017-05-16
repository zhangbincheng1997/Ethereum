package com.redhat.event;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.tx.Contract;

/**
 * @author littleredhat
 */
public class CrowdFundingContract extends Contract implements CrowdFundingInterface {

	/**
	 * 众筹合约
	 * 
	 * @param contractAddress
	 *            合约地址
	 * @param web3j
	 *            JSON-RPC请求
	 * @param credentials
	 *            发起者私钥
	 * @param gasPrice
	 *            gas价格
	 * @param gasLimit
	 *            gas上限
	 */
	public CrowdFundingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	// 合约二进制形式
	private static String BINARY = "6060604052341561000c57fe5b60405160208061021b833981016040528080519060200190919050505b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b505b61019f8061007c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680636d4ce63c14610046578063b60d428814610058575bfe5b341561004e57fe5b610056610062565b005b610060610160565b005b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6001549081150290604051809050600060405180830381858888f1935050505015156100c657fe5b7f872b0148b7fe539f80bf003dc7f7e0138abefdeec118d9773b68ca17385fbebc600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600154604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a160006001819055505b565b346001600082825401925050819055505b5600a165627a7a723058205635d3611ae57725f87dd12db595175aa772c689fa500f00f9ed229a6b1e8dbc0029";

	/**
	 * 部署合约
	 * 
	 * @param web3j
	 *            JSON-RPC请求
	 * @param credentials
	 *            发起者私钥
	 * @param gasPrice
	 *            gas价格
	 * @param gasLimit
	 *            gas上限
	 * @param initialValue
	 *            初始金币
	 * @return
	 */
	public static Future<CrowdFundingContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit, BigInteger initialValue, Address beneficiary) {
		String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(beneficiary));
		return deployAsync(CrowdFundingContract.class, web3j, credentials, gasPrice, gasLimit, BINARY,
				encodedConstructor, initialValue);
	}

	public TransactionReceipt fund(BigInteger value) {
		Function function = new Function("fund", Arrays.asList(), Arrays.<TypeReference<?>>asList());
		try {
			return executeTransaction(FunctionEncoder.encode(function), value);
		} catch (ExecutionException | InterruptedException | TransactionTimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Future<TransactionReceipt> get() {
		Function function = new Function("get", Arrays.asList(), Arrays.<TypeReference<?>>asList());
		return executeTransactionAsync(function);
	}

	public EventValues processCrowdEndEvent(TransactionReceipt future) {
		/*
		 * String name 函数名字 List<TypeReference<?>> indexedParameters 含索引的参数
		 * List<TypeReference<?>> nonIndexedParameters 不含索引的参数
		 */
		Event event = new Event("CrowdEnd", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
		return extractEventParameters(event, future).get(0);
	}
}
