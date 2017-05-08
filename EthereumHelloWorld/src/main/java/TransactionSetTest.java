import java.math.BigInteger;
import java.util.Arrays;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

/**
 * @author littleredhat
 * @description HelloWorld之set函数
 */
public class TransactionSetTest {
	// 钱包密码
	private static String password = "123456";
	// 钱包路径
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// HelloWorld智能合约地址
	private static String toAddress = "0x5a4dc569C7B395130c58A9B0C183fEf6c4957AA9";
	// Gas价格
	private static int gasPrice = 120000;
	// Gas上限
	private static int gasLimit = 200000;

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		
		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(password, path);
		
		// get the next available nonce
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
		
		/*
		 * String name 函数名字
		 * List<Type> inputParameters 入口参数
		 * List<TypeReference<?>> outputParameters 出口参数 
		 */
		Function function = new Function("set", 
				Arrays.asList(new Uint256(BigInteger.valueOf(9981))),
				Arrays.<TypeReference<?>>asList(new TypeReference<Type>() {}));
		
		// encode the function
		String encodedFunction = FunctionEncoder.encode(function);
		
		/*
		 * BigInteger nonce 随机数字
		 * BigInteger gasPrice 价格
		 * BigInteger gasLimit 上限
		 * String to 合约地址
		 * String data 编码函数
		 */
		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, BigInteger.valueOf(gasPrice),
				BigInteger.valueOf(gasLimit), toAddress, encodedFunction);
		
		// sign our transaction
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);
		
		// send our transaction
		EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		
		System.out.println(transactionResponse.getResult());
	}
}
