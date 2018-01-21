package com.redhat.helloworld.contract;

import com.redhat.helloworld.util.Consts;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

/**
 * @author littleredhat
 * <p>
 * 部署合约方式：
 * 一、wallet 部署
 * 二、geth 部署
 * 三、web3j 部署
 */
public class HelloWorldMain {

    public static void main(String[] args) throws Exception {
        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService());

        // 部署合约
        // HelloWorldContract contract = HelloWorldContract.deploy(web3j, credentials,
        //         Consts.GAS_PRICE, Consts.GAS_LIMIT).send();
        // System.out.println("getContractAddress : " + contract.getContractAddress());

        // 加载合约
        HelloWorldContract contract = HelloWorldContract.load(Consts.HELLOWORLD_ADDR, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + contract.getContractAddress());

        ////////// 同步请求方式 //////////
        // set
        TransactionReceipt transactionReceipt = contract.set(10000).send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("set : " + transactionReceipt.getTransactionHash());
        // get
        Uint256 result = contract.get().send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("get : " + result.getValue().intValue());

        ////////// 异步请求方式 //////////
        // set
        /*
        CompletableFuture<TransactionReceipt> transactionReceiptAsync = contract.set(10000).sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("set : " + transactionReceiptAsync.get().getTransactionHash());
        */
        // get
        /*
        CompletableFuture<Uint256> resultAsync = contract.get().sendAsync();
        System.out.println("waiting..."); // 马上返回
        System.out.println("get : " + resultAsync.get().getValue().intValue());
        */
    }
}
