package com.example.demo.contract;

import com.example.demo.util.Constants;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.model.HelloWorld;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

public class HelloWorldDeploy {

    public static void main(String[] args) throws Exception {
        // 启动客户端
        Web3j web3j = Web3j.build(new HttpService(Constants.URL));
        Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD, Constants.SOURCE);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // 部署合约
        HelloWorld contract = HelloWorld.deploy(web3j, credentials, new DefaultGasProvider()).send();
        System.out.println("getContractAddress : " + contract.getContractAddress());

        // rewrite: contractAddress ----> application.properties
    }
}
