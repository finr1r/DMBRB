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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class DMBRBProxy extends Contract {
    private static final String BINARY = "0x6060604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611020806100536000396000f3006060604052600436106100c5576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806308c8c52f146100d55780632171460514610126578063232a1f651461017757806341c0e1b5146101cc578063485e3e85146101e157806359b910d61461023657806382e22c7f1461028757806385aa92a7146102d85780638da5cb5b1461032d5780639eee480614610382578063a6f9dae1146103d7578063ca5dc34214610428578063f3d3d4481461047d575b34156100d057600080fd5b600080fd5b34156100e057600080fd5b61010c600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506104ce565b604051808215151515815260200191505060405180910390f35b341561013157600080fd5b61015d600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610661565b604051808215151515815260200191505060405180910390f35b341561018257600080fd5b61018a610816565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156101d757600080fd5b6101df61083c565b005b34156101ec57600080fd5b6101f46108d1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561024157600080fd5b61026d600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506108f7565b604051808215151515815260200191505060405180910390f35b341561029257600080fd5b6102be600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610aac565b604051808215151515815260200191505060405180910390f35b34156102e357600080fd5b6102eb610c61565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561033857600080fd5b610340610c87565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561038d57600080fd5b610395610cac565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156103e257600080fd5b61040e600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610cd2565b604051808215151515815260200191505060405180910390f35b341561043357600080fd5b61043b610e19565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561048857600080fd5b6104b4600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610e3f565b604051808215151515815260200191505060405180910390f35b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561052c57600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff161415151561055357600080fd5b83915083600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ffa466f02386386671122d8220e27eb744e289f5044e41fcf8b9c2a29ad08d49a828560405180807f426f6f6b734372617465000000000000000000000000000000000000000000008152506020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1600192505050919050565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106bf57600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff16141515156106e657600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915083600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ffa466f02386386671122d8220e27eb744e289f5044e41fcf8b9c2a29ad08d49a828560405180807f5769746864726177616c734372617465000000000000000000000000000000008152506020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1600192505050919050565b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561089757600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561095557600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff161415151561097c57600080fd5b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915083600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ffa466f02386386671122d8220e27eb744e289f5044e41fcf8b9c2a29ad08d49a828560405180807f53746f72616765000000000000000000000000000000000000000000000000008152506020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1600192505050919050565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610b0a57600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff1614151515610b3157600080fd5b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915083600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ffa466f02386386671122d8220e27eb744e289f5044e41fcf8b9c2a29ad08d49a828560405180807f446562746f7273437261746500000000000000000000000000000000000000008152506020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1600192505050919050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610d2f57600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515610d5557600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da260405160405180910390a3816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610e9d57600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff1614151515610ec457600080fd5b600460009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16915083600460006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507ffa466f02386386671122d8220e27eb744e289f5044e41fcf8b9c2a29ad08d49a828560405180807f444d425242436f6e74726f6c6c657200000000000000000000000000000000008152506020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a16001925050509190505600a165627a7a72305820e01b997b9c336fe5e7eb9b6a6e10e3486c6d6b95cff5f2a5fbc534d93c18b4480029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected DMBRBProxy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DMBRBProxy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeAddressEventResponse> getLogChangeAddressEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeAddress", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeAddressEventResponse> responses = new ArrayList<LogChangeAddressEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeAddressEventResponse typedResponse = new LogChangeAddressEventResponse();
            typedResponse.contractName = (Bytes32) eventValues.getNonIndexedValues().get(0);
            typedResponse.oldAddress = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.newAddress = (Address) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeAddressEventResponse> logChangeAddressEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeAddress", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeAddressEventResponse>() {
            @Override
            public LogChangeAddressEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeAddressEventResponse typedResponse = new LogChangeAddressEventResponse();
                typedResponse.contractName = (Bytes32) eventValues.getNonIndexedValues().get(0);
                typedResponse.oldAddress = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.newAddress = (Address) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
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

    public RemoteCall<TransactionReceipt> setBooksCrateAddress(Address _booksCrate) {
        Function function = new Function(
                "setBooksCrateAddress", 
                Arrays.<Type>asList(_booksCrate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setWithdrawalsCrateAddress(Address _withdrawalsCrate) {
        Function function = new Function(
                "setWithdrawalsCrateAddress", 
                Arrays.<Type>asList(_withdrawalsCrate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> dmbrbController() {
        Function function = new Function("dmbrbController", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> booksCrate() {
        Function function = new Function("booksCrate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setStorageAddress(Address _storage) {
        Function function = new Function(
                "setStorageAddress", 
                Arrays.<Type>asList(_storage), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setDebtorsCrateAddress(Address _debtorsCrate) {
        Function function = new Function(
                "setDebtorsCrateAddress", 
                Arrays.<Type>asList(_debtorsCrate), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> storageAddress() {
        Function function = new Function("storageAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> debtorsCrate() {
        Function function = new Function("debtorsCrate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> changeOwner(Address newOwner) {
        Function function = new Function(
                "changeOwner", 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> withdrawalsCrate() {
        Function function = new Function("withdrawalsCrate", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setControllerAddress(Address _dmbrbController) {
        Function function = new Function(
                "setControllerAddress", 
                Arrays.<Type>asList(_dmbrbController), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<DMBRBProxy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DMBRBProxy.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<DMBRBProxy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DMBRBProxy.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static DMBRBProxy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DMBRBProxy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DMBRBProxy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DMBRBProxy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeAddressEventResponse {
        public Bytes32 contractName;

        public Address oldAddress;

        public Address newAddress;
    }

    public static class LogChangeOwnerEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
