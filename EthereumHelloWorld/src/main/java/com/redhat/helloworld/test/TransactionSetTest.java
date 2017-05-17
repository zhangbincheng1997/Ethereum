package com.redhat.helloworld.test;

import java.math.BigInteger;
import java.util.Arrays;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import com.redhat.helloworld.util.Consts;

/**
 * @author littleredhat
 */
public class TransactionSetTest {

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

		// get the next available nonce
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		/*
		 * String name 函数名字
		 * List<Type> inputParameters 入口参数
		 * List<TypeReference<?>> outputParameters 出口参数
		 */
		Function function = new Function("set", Arrays.asList(new Uint256(BigInteger.valueOf(10000))),
				Arrays.<TypeReference<?>>asList());

		// encode the function
		String encodedFunction = FunctionEncoder.encode(function);

		/*
		 * BigInteger nonce 随机数字
		 * BigInteger gasPrice 价格
		 * BigInteger gasLimit 上限
		 * String to 合约地址 String data 编码函数
		 */
		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, BigInteger.valueOf(Consts.GAS_PRICE),
				BigInteger.valueOf(Consts.GAS_LIMIT), Consts.HELLOWORLD_CONTRACT_ADDRESS, encodedFunction);

		// sign our transaction
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);

		// send our transaction
		EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		System.out.println(transactionResponse.getResult());
	}
}
