package com.example.demo.contract;

import com.example.demo.util.Constants;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.model.HelloWorld;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class HelloWorldMain {

    public static void main(String[] args) throws Exception {
        // 启动客户端
        Web3j web3j = Web3j.build(new HttpService(Constants.URL));
        Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD, Constants.SOURCE);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // 加载合约
        HelloWorld contract = HelloWorld.load(Constants.ADDRESS, web3j, credentials, new DefaultGasProvider());
        System.out.println("getContractAddress : " + contract.getContractAddress());

        //***** 同步请求方式 *****
        // set
        contract.set(BigInteger.valueOf(10000)).send();
        System.out.println("waiting..."); // 阻塞
        System.out.println("set : " + 10000);
        // get
        BigInteger result = contract.get().send();
        System.out.println("waiting..."); // 阻塞
        System.out.println("get : " + result); // 10000

        //***** 异步请求方式 *****
        // set
        contract.set(BigInteger.valueOf(20000)).sendAsync();
        System.out.println("waiting..."); // 非阻塞
        System.out.println("set : " + 20000);

        // get
        CompletableFuture<BigInteger> future = contract.get().sendAsync();
        System.out.println("waiting..."); // 非阻塞
        System.out.println("get : " + future.get()); // 10000
    }
}
