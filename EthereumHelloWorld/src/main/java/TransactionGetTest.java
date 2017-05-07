import java.util.Arrays;
import java.util.List;

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

/**
 * @author littleredhat
 * 
 *         HelloWorld之get函数
 */
public class TransactionGetTest {

	private static String toAddress = "0x5a4dc569C7B395130c58A9B0C183fEf6c4957AA9";

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());

		/*
		 * String name 函数名字
		 * List<Type> inputParameters 入口参数
		 * List<TypeReference<?>> outputParameters 出口参数
		 */
		Function function = new Function("get", Arrays.asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));

		// encode the function
		String encodedFunction = FunctionEncoder.encode(function);

		/*
		 * eth_call allows you to call a method on a smart contract to query a value.
		 * There is no transaction cost associated with this function,
		 * this is because it does not change the state of any smart contract method’s called,
		 * it simply returns the value from them.
		 */
		EthCall response = web3j.ethCall(Transaction.createEthCallTransaction(toAddress, encodedFunction),
				DefaultBlockParameterName.LATEST).sendAsync().get();

		// get result
		List<Type> result = FunctionReturnDecoder.decode(response.getValue(), function.getOutputParameters());
		int retVal = Integer.parseInt(result.get(0).getValue().toString());

		System.out.println("retVal = " + retVal);
	}
}
