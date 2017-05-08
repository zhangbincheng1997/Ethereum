import java.io.File;

import org.web3j.crypto.WalletUtils;

/**
 * @author littleredhat
 * @description 创建钱包
 */
public class CreateTest {
	// 钱包密码
	private static String password = "123456";
	// 钱包路径
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore";

	public static void main(String[] args) throws Exception {
		// To create a new wallet file
		String fileName = WalletUtils.generateNewWalletFile(password, new File(path), true);
		System.out.println(fileName);
	}
}
