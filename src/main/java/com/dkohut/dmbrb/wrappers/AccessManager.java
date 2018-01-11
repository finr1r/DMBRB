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
public class AccessManager extends Contract {
    private static final String BINARY = "0x6060604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610830806100536000396000f30060606040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635cb8dd091461007257806385c53cf1146100d05780638da5cb5b1461012e578063a6f9dae114610183578063bd17a9d8146101d4575b600080fd5b341561007d57600080fd5b6100b6600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803560001916906020019091905050610232565b604051808215151515815260200191505060405180910390f35b34156100db57600080fd5b610114600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035600019169060200190919050506102a2565b604051808215151515815260200191505060405180910390f35b341561013957600080fd5b61014161049d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561018e57600080fd5b6101ba600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919050506104c2565b604051808215151515815260200191505060405180910390f35b34156101df57600080fd5b610218600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803560001916906020019091905050610609565b604051808215151515815260200191505060405180910390f35b6000600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000836000191660001916815260200190815260200160002060009054906101000a900460ff16905092915050565b60008260008173ffffffffffffffffffffffffffffffffffffffff16141515156102cb57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561032657600080fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000846000191660001916815260200190815260200160002060009054906101000a900460ff1615151561039857600080fd5b60018060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000856000191660001916815260200190815260200160002060006101000a81548160ff0219169083151502179055503073ffffffffffffffffffffffffffffffffffffffff167f42cc9a73cb66d206fa52aeef09b695777f6b2ca3eff92d4f5b43a34006e12c188585604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182600019166000191681526020019250505060405180910390a2600191505092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561051f57600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff161415151561054557600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da260405160405180910390a3816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60008260008173ffffffffffffffffffffffffffffffffffffffff161415151561063257600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561068d57600080fd5b600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000846000191660001916815260200190815260200160002060009054906101000a900460ff1615156106fe57600080fd5b6000600160008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000856000191660001916815260200190815260200160002060006101000a81548160ff0219169083151502179055503073ffffffffffffffffffffffffffffffffffffffff167f73925371d4595e944b8657e22b61dbe8f6114913c27cd477cd66fbce7e615e278585604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182600019166000191681526020019250505060405180910390a26001915050929150505600a165627a7a72305820e5fdbc3736601483b25f42940508956bf713bffcd218386d59e5c3f03c99dfd10029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected AccessManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AccessManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogAccessGrantedEventResponse> getLogAccessGrantedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogAccessGranted", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogAccessGrantedEventResponse> responses = new ArrayList<LogAccessGrantedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogAccessGrantedEventResponse typedResponse = new LogAccessGrantedEventResponse();
            typedResponse.self = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.actor = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.role = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogAccessGrantedEventResponse> logAccessGrantedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogAccessGranted", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogAccessGrantedEventResponse>() {
            @Override
            public LogAccessGrantedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogAccessGrantedEventResponse typedResponse = new LogAccessGrantedEventResponse();
                typedResponse.self = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.actor = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.role = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogAccessDeniedEventResponse> getLogAccessDeniedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogAccessDenied", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogAccessDeniedEventResponse> responses = new ArrayList<LogAccessDeniedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogAccessDeniedEventResponse typedResponse = new LogAccessDeniedEventResponse();
            typedResponse.self = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.actor = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.role = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogAccessDeniedEventResponse> logAccessDeniedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogAccessDenied", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogAccessDeniedEventResponse>() {
            @Override
            public LogAccessDeniedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogAccessDeniedEventResponse typedResponse = new LogAccessDeniedEventResponse();
                typedResponse.self = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.actor = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.role = (Bytes32) eventValues.getNonIndexedValues().get(1);
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

    public RemoteCall<Bool> isAllowed(Address _actor, Bytes32 _role) {
        Function function = new Function("isAllowed", 
                Arrays.<Type>asList(_actor, _role), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> giveAccess(Address _actor, Bytes32 _role) {
        Function function = new Function(
                "giveAccess", 
                Arrays.<Type>asList(_actor, _role), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
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

    public RemoteCall<TransactionReceipt> blockAccess(Address _actor, Bytes32 _role) {
        Function function = new Function(
                "blockAccess", 
                Arrays.<Type>asList(_actor, _role), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<AccessManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccessManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<AccessManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(AccessManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static AccessManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccessManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static AccessManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AccessManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogAccessGrantedEventResponse {
        public Address self;

        public Address actor;

        public Bytes32 role;
    }

    public static class LogAccessDeniedEventResponse {
        public Address self;

        public Address actor;

        public Bytes32 role;
    }

    public static class LogChangeOwnerEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
