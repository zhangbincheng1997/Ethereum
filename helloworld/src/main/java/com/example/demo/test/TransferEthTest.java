package com.example.demo.test;

import com.example.demo.util.Constants;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

public class TransferEthTest {
    // 转账地址
    private static final String toAddress = "0x......";
    // 转账金额
    private static final double value = 100.0;

    public static void main(String[] args) throws Exception {
        Web3j web3 = Web3j.build(new HttpService(Constants.URL)); // defaults to http://localhost:8545/
        Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD, Constants.SOURCE);

        // Web3j web3j, Credentials credentials, String toAddress, BigDecimal value, Convert.Unit unit
        TransactionReceipt transactionReceipt = Transfer.sendFunds(
                web3, credentials, toAddress,
                BigDecimal.valueOf(value), Convert.Unit.ETHER)
                .send();
        System.out.println(transactionReceipt.getTransactionHash());
    }
}
