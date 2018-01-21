package com.redhat.helloworld.contract;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author littleredhat
 */
public class HelloWorldContract extends Contract implements HelloWorldInterface {

    // https://remix.ethereum.org/ Compile - Details - WEB3DEPLOY - data
    private static final String BINARY = "0x6060604052341561000f57600080fd5b60d38061001d6000396000f3006060604052600436106049576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806360fe47b114604e5780636d4ce63c14606e575b600080fd5b3415605857600080fd5b606c60048080359060200190919050506094565b005b3415607857600080fd5b607e609e565b6040518082815260200191505060405180910390f35b8060008190555050565b600080549050905600a165627a7a72305820b287ea3878f6f6cc6e7e3885be10bd9172b2074fbdc32fbfc8f7edd3f683e8d90029";

    /**
     * HelloWorld合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     */
    private HelloWorldContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                               BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * 部署合约
     *
     * @param web3j       RPC请求
     * @param credentials 钱包凭证
     * @param gasPrice    GAS价格
     * @param gasLimit    GAS上限
     * @return
     */
    public static RemoteCall<HelloWorldContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        // 构造函数参数 NULL
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
        return deployRemoteCall(HelloWorldContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    /**
     * 加载合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     * @return
     */
    public static HelloWorldContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorldContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * get
     */
    public RemoteCall<Uint256> get() {
        Function function = new Function("get", Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function);
    }

    /**
     * set
     */
    public RemoteCall<TransactionReceipt> set(int x) {
        Function function = new Function("set", Arrays.<Type>asList(new Uint256(x)),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }
}
