package com.redhat.helloworld.test;

import com.redhat.helloworld.util.Consts;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;

import java.util.Arrays;
import java.util.List;

/**
 * @author littleredhat
 */
public class TransactionGetTest {

    public static void main(String[] args) throws Exception {
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService());

        /*
         * String name 函数名字
         * List<Type> inputParameters 入口参数
         * List<TypeReference<?>> outputParameters 出口参数
         */
        Function function = new Function("get", Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));

        // encode the function
        String encodedFunction = FunctionEncoder.encode(function);

        /*
         * eth_call allows you to call a method on a smart contract to query a value.
         * There is no transaction cost associated with this function,
         * this is because it does not change the state of any smart contract method's called,
         * it simply returns the value from them.
         */
        EthCall response = web3j
                .ethCall(Transaction.createEthCallTransaction(null, Consts.HELLOWORLD_ADDR, encodedFunction),
                        DefaultBlockParameterName.LATEST)
                .send();

        // get result
        List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
        int data = Integer.parseInt(result.get(0).getValue().toString());
        System.out.println("data = " + data);
    }
}
