package test;

import com.redhat.helloworld.util.Consts;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

/**
 * @author littleredhat
 */
public class TransferEthTest {
    // 对方地址
    private static String toAddress = "0x353c05F5b8169CE03c82c7d78c674c1429E26dAc";
    // 转账数量
    private static double value = 100;

    public static void main(String[] args) throws Exception {
        // defaults to http://localhost:8545/
        Web3j web3 = Web3j.build(new HttpService());

        // To send Ether to another party using your Ethereum wallet file
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

        // Web3j web3j, Credentials credentials, String toAddress, BigDecimal
        // value, Convert.Unit unit
        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, toAddress,
                BigDecimal.valueOf(value), Convert.Unit.ETHER).send();
        System.out.println(transactionReceipt.getTransactionHash());
    }
}
