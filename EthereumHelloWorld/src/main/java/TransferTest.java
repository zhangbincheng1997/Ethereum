import java.math.BigDecimal;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

/**
 * @author littleredhat
 * 
 *         账户之间转账
 */
public class TransferTest {

	private static String password = "123456";
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	private static String toAddress = "0x6c079ade6a7f4c74eebd85c272d2b3930f1224a4";

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3 = Web3j.build(new HttpService());
		Credentials credentials = WalletUtils.loadCredentials(password, path);
		Transfer.sendFunds(web3, credentials, toAddress, BigDecimal.valueOf(100.0), Convert.Unit.ETHER);
	}
}
