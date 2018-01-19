package com.redhat.helloworld.test;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

/**
 * @author littleredhat
 */
public class ClientVersionTest {

    public static void main(String[] args) throws Exception {
        // defaults to http://localhost:8545/
        Web3j web3 = Web3j.build(new HttpService());

        // To send synchronous requests
        String clientVersion = web3.web3ClientVersion().send().getWeb3ClientVersion();
        System.out.println(clientVersion);
    }
}
