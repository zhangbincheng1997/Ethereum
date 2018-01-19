package com.redhat.helloworld.contract;

import com.redhat.helloworld.util.Consts;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.CompletableFuture;

/**
 * @author littleredhat
 */
public class HelloWorldMain {

    public static void main(String[] args) throws Exception {
        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService());

        // 获取合约
        HelloWorldContract contract = new HelloWorldContract(Consts.HELLOWORLD_ADDR, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + contract.getContractAddress());

        // set
        CompletableFuture<TransactionReceipt> transferReceipt = contract.set(10000).sendAsync(); // 注意是异步请求
        System.out.println("waiting..."); // 请求完马上返回
        System.out.println("set : " + transferReceipt.get().getTransactionHash()); // 这里的 get 会等待

        // get
        CompletableFuture<Uint256> result = contract.get().sendAsync(); // 注意是异步请求
        System.out.println("waiting..."); // 请求完马上返回
        System.out.println("get : " + result.get().getValue().intValue()); // 这里的 get 会等待
    }
}
