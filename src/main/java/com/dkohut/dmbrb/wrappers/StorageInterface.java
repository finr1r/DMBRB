package com.dkohut.dmbrb.wrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class StorageInterface extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b6103f38061001e6000396000f300606060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063486a8c62146100675780635590e4d21461010b578063934e03a414610141578063ada9a9f014610175575b600080fd5b6100b4600480803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091905050610219565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156100f75780820151818401526020810190506100dc565b505050509050019250505060405180910390f35b610123600480803515159060200190919050506102d8565b60405180826000191660001916815260200191505060405180910390f35b61015b6004808035600019169060200190919050506102f6565b604051808215151515815260200191505060405180910390f35b6101c260048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190505061030e565b6040518080602001828103825283818151815260200191508051906020019060200280838360005b838110156102055780820151818401526020810190506101ea565b505050509050019250505060405180910390f35b61022161039f565b61022961039f565b6000835160405180591061023a5750595b90808252806020026020018201604052509150600090505b83518110156102ce57838181518110151561026957fe5b9060200190602002015160019004828281518110151561028557fe5b9060200190602002019073ffffffffffffffffffffffffffffffffffffffff16908173ffffffffffffffffffffffffffffffffffffffff16815250508080600101915050610252565b8192505050919050565b6000816102e65760006102e9565b60015b60ff166001029050919050565b60008060010260001916826000191614159050919050565b6103166103b3565b61031e6103b3565b6000835160405180591061032f5750595b90808252806020026020018201604052509150600090505b835181101561039557838181518110151561035e57fe5b9060200190602002015160019004828281518110151561037a57fe5b90602001906020020181815250508080600101915050610347565b8192505050919050565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a72305820845439668e940342493afd526e6adfa9d151077dee370f4093f4db49ab536d0f0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected StorageInterface(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StorageInterface(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<DynamicArray<Address>> toAddresses(DynamicArray<Bytes32> self) {
        Function function = new Function("toAddresses", 
                Arrays.<Type>asList(self), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bytes32> toBytes32(Bool self) {
        Function function = new Function("toBytes32", 
                Arrays.<Type>asList(self), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> toBool(Bytes32 self) {
        Function function = new Function("toBool", 
                Arrays.<Type>asList(self), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<DynamicArray<Uint256>> toUInt(DynamicArray<Bytes32> self) {
        Function function = new Function("toUInt", 
                Arrays.<Type>asList(self), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<StorageInterface> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StorageInterface.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<StorageInterface> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(StorageInterface.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static StorageInterface load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StorageInterface(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static StorageInterface load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StorageInterface(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
