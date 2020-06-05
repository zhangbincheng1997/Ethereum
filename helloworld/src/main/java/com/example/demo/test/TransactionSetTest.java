package com.example.demo.test;

import com.example.demo.util.Constants;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class TransactionSetTest {

    public static final BigInteger GAS_PRICE = BigInteger.valueOf(4100000000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(9000000L);

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
        Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD, Constants.SOURCE);

        // get the next available nonce
        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        /*
         * String name 函数名字
         * List<Type> inputParameters 入口参数
         * List<TypeReference<?>> outputParameters 出口参数
         */
        Function function = new Function("set",
                Arrays.<Type>asList(new Uint256(BigInteger.valueOf(10000))),
                Collections.<TypeReference<?>>emptyList());

        // encode the function
        String encodedFunction = FunctionEncoder.encode(function);

        /*
         * BigInteger nonce 随机数
         * BigInteger gasPrice gas价格
         * BigInteger gasLimit gas上限
         * String to 合约地址
         * String data 数据
         */
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, GAS_PRICE, GAS_LIMIT, Constants.ADDRESS, encodedFunction);

        // sign our transaction
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);
        System.out.println(hexValue);

        // send our transaction
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println(transactionHash);
    }
}
