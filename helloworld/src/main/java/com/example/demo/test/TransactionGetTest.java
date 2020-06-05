package com.example.demo.test;

import com.example.demo.util.Constants;
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

public class TransactionGetTest {

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService()); // defaults to http://localhost:8545/

        /*
         * String name 函数名字
         * List<Type> inputParameters 入口参数
         * List<TypeReference<?>> outputParameters 出口参数
         */
        Function function = new Function("get",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));

        // encode the function
        String encodedFunction = FunctionEncoder.encode(function);

        /*
         * String from null(optional)
         * String to 合约地址
         * String data ABI
         */
        EthCall response = web3j.ethCall(Transaction.createEthCallTransaction(null, Constants.ADDRESS, encodedFunction), DefaultBlockParameterName.LATEST).send();

        // get result
        List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
        int data = Integer.parseInt(result.get(0).getValue().toString());
        System.out.println("data = " + data);
    }
}
