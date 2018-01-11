package com.dkohut.dmbrb.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class Storage extends Contract {
    private static final String BINARY = "0x6060604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506111b6806100536000396000f3006060604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063061c8800146100eb578063252edb761461012857806337d9d7fc14610168578063428e357b146101b8578063481c6a751461020657806353aa3f5e1461025b57806353e7168b146102a357806379a7cfee146102e05780638da5cb5b1461033357806398eaf11c14610388578063a14ff857146103d4578063a6f9dae114610415578063a6fbf3d214610466578063d0ebdbe7146104ae578063d3a39686146104ff578063f0f53ee414610573575b600080fd5b34156100f657600080fd5b610126600480803560001916906020019091908035600019169060200190919080359060200190919050506105b2565b005b341561013357600080fd5b6101666004808035600019169060200190919080356000191690602001909190803560ff169060200190919050506106f0565b005b341561017357600080fd5b61019a60048080356000191690602001909190803560001916906020019091905050610842565b60405180826000191660001916815260200191505060405180910390f35b34156101c357600080fd5b6101ea60048080356000191690602001909190803560001916906020019091905050610884565b604051808260ff1660ff16815260200191505060405180910390f35b341561021157600080fd5b6102196108d3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561026657600080fd5b61028d600480803560001916906020019091908035600019169060200190919050506108f9565b6040518082815260200191505060405180910390f35b34156102ae57600080fd5b6102de6004808035600019169060200190919080356000191690602001909190803590602001909190505061093b565b005b34156102eb57600080fd5b6103316004808035600019169060200190919080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610a79565b005b341561033e57600080fd5b610346610bf1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561039357600080fd5b6103ba60048080356000191690602001909190803560001916906020019091905050610c16565b604051808215151515815260200191505060405180910390f35b34156103df57600080fd5b6104136004808035600019169060200190919080356000191690602001909190803560001916906020019091905050610c65565b005b341561042057600080fd5b61044c600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610da7565b604051808215151515815260200191505060405180910390f35b341561047157600080fd5b61049860048080356000191690602001909190803560001916906020019091905050610eee565b6040518082815260200191505060405180910390f35b34156104b957600080fd5b6104e5600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610f30565b604051808215151515815260200191505060405180910390f35b341561050a57600080fd5b61053160048080356000191690602001909190803560001916906020019091905050610fd7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561057e57600080fd5b6105b0600480803560001916906020019091908035600019169060200190919080351515906020019091905050611039565b005b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b151561068857600080fd5b6102c65a03f1151561069957600080fd5b5050506040518051905015156106ae57600080fd5b81600160008660001916600019168152602001908152602001600020600301600085600019166000191681526020019081526020016000208190555050505050565b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b15156107c657600080fd5b6102c65a03f115156107d757600080fd5b5050506040518051905015156107ec57600080fd5b816001600086600019166000191681526020019081526020016000206004016000856000191660001916815260200190815260200160002060006101000a81548160ff021916908360ff16021790555050505050565b60006001600084600019166000191681526020019081526020016000206005016000836000191660001916815260200190815260200160002054905092915050565b60006001600084600019166000191681526020019081526020016000206004016000836000191660001916815260200190815260200160002060009054906101000a900460ff16905092915050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006001600084600019166000191681526020019081526020016000206000016000836000191660001916815260200190815260200160002054905092915050565b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b1515610a1157600080fd5b6102c65a03f11515610a2257600080fd5b505050604051805190501515610a3757600080fd5b81600160008660001916600019168152602001908152602001600020600001600085600019166000191681526020019081526020016000208190555050505050565b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b1515610b4f57600080fd5b6102c65a03f11515610b6057600080fd5b505050604051805190501515610b7557600080fd5b816001600086600019166000191681526020019081526020016000206001016000856000191660001916815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006001600084600019166000191681526020019081526020016000206002016000836000191660001916815260200190815260200160002060009054906101000a900460ff16905092915050565b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b1515610d3b57600080fd5b6102c65a03f11515610d4c57600080fd5b505050604051805190501515610d6157600080fd5b8160016000866000191660001916815260200190815260200160002060050160008560001916600019168152602001908152602001600020816000191690555050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e0457600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515610e2a57600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da260405160405180910390a3816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60006001600084600019166000191681526020019081526020016000206003016000836000191660001916815260200190815260200160002054905092915050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610f8d57600080fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60006001600084600019166000191681526020019081526020016000206001016000836000191660001916815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905092915050565b82600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd0933836000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b151561110f57600080fd5b6102c65a03f1151561112057600080fd5b50505060405180519050151561113557600080fd5b816001600086600019166000191681526020019081526020016000206002016000856000191660001916815260200190815260200160002060006101000a81548160ff021916908315150217905550505050505600a165627a7a723058200b3e4e55aba5899bb7b416fdf42cde860a92a9f0ad83421b40bfbdb39a208c3c0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected Storage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Storage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeOwnerEventResponse> getLogChangeOwnerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeOwnerEventResponse> responses = new ArrayList<LogChangeOwnerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
            typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeOwnerEventResponse> logChangeOwnerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeOwnerEventResponse>() {
            @Override
            public LogChangeOwnerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
                typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> setInt(Bytes32 _crate, Bytes32 _key, Int256 _value) {
        Function function = new Function(
                "setInt", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUInt8(Bytes32 _crate, Bytes32 _key, Uint8 _value) {
        Function function = new Function(
                "setUInt8", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bytes32> getBytes32(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getBytes32", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint8> getUInt8(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getUInt8", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> manager() {
        Function function = new Function("manager", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUInt(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getUInt", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setUInt(Bytes32 _crate, Bytes32 _key, Uint256 _value) {
        Function function = new Function(
                "setUInt", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setAddress(Bytes32 _crate, Bytes32 _key, Address _value) {
        Function function = new Function(
                "setAddress", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> getBool(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getBool", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setBytes32(Bytes32 _crate, Bytes32 _key, Bytes32 _value) {
        Function function = new Function(
                "setBytes32", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeOwner(Address newOwner) {
        Function function = new Function(
                "changeOwner", 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Int256> getInt(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getInt", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setManager(Address _manager) {
        Function function = new Function(
                "setManager", 
                Arrays.<Type>asList(_manager), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getAddress(Bytes32 _crate, Bytes32 _key) {
        Function function = new Function("getAddress", 
                Arrays.<Type>asList(_crate, _key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setBool(Bytes32 _crate, Bytes32 _key, Bool _value) {
        Function function = new Function(
                "setBool", 
                Arrays.<Type>asList(_crate, _key, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Storage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Storage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Storage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Storage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Storage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeOwnerEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
