package test;

import com.redhat.helloworld.util.Consts;
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

/**
 * @author littleredhat
 */
public class TransactionSetTest {

    public static void main(String[] args) throws Exception {
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService());

        // load the credentials
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

        // get the next available nonce
        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        /*
         * String name 函数名字
         * List<Type> inputParameters 入口参数
         * List<TypeReference<?>> outputParameters 出口参数
         */
        Function function = new Function("set", Arrays.<Type>asList(new Uint256(BigInteger.valueOf(10000))),
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
        RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, Consts.GAS_PRICE, Consts.GAS_LIMIT,
                Consts.HELLOWORLD_ADDR, encodedFunction);

        // sign our transaction
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        // send our transaction
        EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue).send();
        System.out.println(transactionResponse.getResult());
    }
}
