package com.example.demo.test;

import com.example.demo.util.Constants;
import io.reactivex.disposables.Disposable;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.model.HelloWorld;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FilterTest {
    // 区块数量
    private static int blockNumber = 0;
    // 交易数量
    private static int transactionNumber = 0;
    // 待处理交易数量
    private static int pendingTransactionNumber = 0;

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService()); // defaults to http://localhost:8545/
        Credentials credentials = WalletUtils.loadCredentials(Constants.PASSWORD, Constants.SOURCE);
        HelloWorld contract = HelloWorld.load(Constants.ADDRESS, web3j, credentials, new DefaultGasProvider());
        System.out.println("getContractAddress : " + contract.getContractAddress());

        //***** 过滤器 *****
        Disposable block = BlockFilter(web3j);
        Disposable transaction = TransactionFilter(web3j);
        Disposable pendingTransaction = PendingTransactionFilter(web3j);

        //***** 10次交易 *****
        for (int i = 0; i < 10; i++) {
            // set
            contract.set(BigInteger.valueOf(i)).send();
            System.out.println("waiting...");
            System.out.println("set : " + i);

            // get
            BigInteger result = contract.get().send();
            System.out.println("waiting...");
            System.out.println("get : " + result);
        }

        //***** 停止订阅 *****
        if (block.isDisposed()) block.dispose();
        if (transaction.isDisposed()) transaction.dispose();
        if (pendingTransaction.isDisposed()) pendingTransaction.dispose();
    }

    private static Disposable BlockFilter(Web3j web3j) {
        return web3j.blockFlowable(false).subscribe(block -> {
            System.out.println("blockNumber : " + ++blockNumber);
            LocalDateTime timestamp = Instant.ofEpochSecond(block.getBlock().getTimestamp().longValueExact())
                    .atZone(ZoneId.of("UTC")).toLocalDateTime();
            System.out.println("timestamp : " + timestamp);
            System.out.println("hash : " + block.getBlock().getHash());
        });
    }

    private static Disposable TransactionFilter(Web3j web3j) {
        return web3j.transactionFlowable().subscribe(tx -> {
            System.out.println("transactionNumber : " + ++transactionNumber);
            System.out.println("getHash : " + tx.getHash());
            System.out.println("getBlockHash : " + tx.getBlockHash());
        });
    }

    private static Disposable PendingTransactionFilter(Web3j web3j) {
        return web3j.pendingTransactionFlowable().subscribe(tx -> {
            System.out.println("pendingTransactionNumber : " + ++pendingTransactionNumber);
            System.out.println("getHash : " + tx.getHash());
            System.out.println("getBlockHash : " + tx.getBlockHash());
        });
    }
}
