package com.redhat.sample;

import java.io.File;

import org.web3j.crypto.WalletUtils;

/**
 * @author littleredhat
 */
public class GenerateWalletSample {
	// Ç®°üÃÜÂë
	private static String password = "123456";
	// Ç®°üÂ·¾¶
	private static String destinationDirectory = "F:\\chain\\geth-win64\\chain\\keystore";

	public static void main(String[] args) throws Exception {
		// To create a new wallet file
		String fileName = WalletUtils.generateNewWalletFile(password, new File(destinationDirectory), true);
		System.out.println(fileName);
	}
}
