package com.redhat.helloworld.contract;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface HelloWorldInterface {

    // get
    public RemoteCall<Uint256> get();

    // set
    public RemoteCall<TransactionReceipt> set(int x);
}
