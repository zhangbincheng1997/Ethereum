package com.redhat.helloworld.test;

import java.io.File;

import org.web3j.crypto.WalletUtils;

import com.redhat.helloworld.util.Consts;

/**
 * @author littleredhat
 */
public class GenerateWalletTest {

	public static void main(String[] args) throws Exception {
		// To create a new wallet file
		// String password, File destinationDirectory, boolean useFullScrypt
		String fileName = WalletUtils.generateNewWalletFile(Consts.PASSWORD, new File(Consts.DIRECTORY), true);
		System.out.println(fileName);
	}
}
