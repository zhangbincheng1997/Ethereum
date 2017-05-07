import java.io.File;

import org.web3j.crypto.WalletUtils;

/**
 * @author littleredhat
 * 
 *         创建钱包账户
 */
public class CreateTest {

	private static String password = "123456";
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore";

	public static void main(String[] args) throws Exception {
		String fileName = WalletUtils.generateNewWalletFile(password, new File(path), true);
		System.out.println(fileName);
	}
}
