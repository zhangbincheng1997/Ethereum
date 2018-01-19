package contract;

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

    /**
     * HelloWorld合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     */
    public HelloWorldContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                              BigInteger gasLimit) {
        super("", contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * get
     */
    public RemoteCall<BigInteger> get() {
        Function function = new Function("get", Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    /**
     * set
     */
    public RemoteCall<TransactionReceipt> set(int x) {
        Function function = new Function("set", Arrays.<Type>asList(new Uint256(BigInteger.valueOf(x))),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }
}
