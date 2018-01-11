package com.dkohut.dmbrb.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class DebtorsCrate extends Contract {
    private static final String BINARY = "0x60606040527f436f6e74726f6c6c657200000000000000000000000000000000000000000000600190600019169055341561003957600080fd5b604051604080610dd4833981016040528080519060200190919080519060200190919050508181336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600102600019168160001916141515156100b857fe5b6100db8282600361012564010000000002610aec17909291906401000000009004565b505061011e7f646562746f727300000000000000000000000000000000000000000000000000600561017a64010000000002610b41179091906401000000009004565b50506101fc565b818360000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080836001018160001916905550505050565b61019a826000018261019e64010000000002610b52176401000000009004565b5050565b6101bf8260000154826101d064010000000002610b71176401000000009004565b808260000181600019169055505050565b600060010282600019161415806101ee575060006001028160001916145b156101f857600080fd5b5050565b610bc98061020b6000396000f300606060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e02e5b81461009357806341c0e1b5146100ed578063481c6a75146101025780638da5cb5b14610157578063a6f9dae1146101ac578063d0ebdbe7146101fd578063ee1402361461024e575b341561008e57600080fd5b600080fd5b341561009e57600080fd5b6100d3600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001909190505061029b565b604051808215151515815260200191505060405180910390f35b34156100f857600080fd5b610100610433565b005b341561010d57600080fd5b6101156104c8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561016257600080fd5b61016a6104ee565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156101b757600080fd5b6101e3600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610513565b604051808215151515815260200191505060405180910390f35b341561020857600080fd5b610234600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061065a565b604051808215151515815260200191505060405180910390f35b341561025957600080fd5b610285600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610729565b6040518082815260200191505060405180910390f35b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd09336001546000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b151561037457600080fd5b6102c65a03f1151561038557600080fd5b50505060405180519050151561039a57600080fd5b8260008173ffffffffffffffffffffffffffffffffffffffff16141515156103c157600080fd5b6103da60058585600361086e909392919063ffffffff16565b8373ffffffffffffffffffffffffffffffffffffffff167f4d4f87481621af58356077e73fbb3a68d6d585aeffa7301aea56273d3c8df549846040518082815260200191505060405180910390a2600191505092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561048e57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561057057600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff161415151561059657600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da260405160405180910390a3816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106b757600080fd5b8160008173ffffffffffffffffffffffffffffffffffffffff16141515156106de57600080fd5b82600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001915050919050565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd09336001546000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b151561080257600080fd5b6102c65a03f1151561081357600080fd5b50505060405180519050151561082857600080fd5b8160008173ffffffffffffffffffffffffffffffffffffffff161415151561084f57600080fd5b610866600584600361089f9092919063ffffffff16565b915050919050565b61089984846000018473ffffffffffffffffffffffffffffffffffffffff16600102846001026108d5565b50505050565b60006108c884846000018473ffffffffffffffffffffffffffffffffffffffff166001026109de565b6001900490509392505050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a14ff85785600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808460001916600019168152602001836000191660001916815260200182600019166000191681526020019350505050600060405180830381600087803b15156109c457600080fd5b6102c65a03f115156109d557600080fd5b50505050505050565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166337d9d7fc856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b1515610ac857600080fd5b6102c65a03f11515610ad957600080fd5b5050506040518051905090509392505050565b818360000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080836001018160001916905550505050565b610b4e8260000182610b52565b5050565b610b60826000015482610b71565b808260000181600019169055505050565b60006001028260001916141580610b8f575060006001028160001916145b15610b9957600080fd5b50505600a165627a7a723058208a1d1b06cf45a00a104ce76783acf34b0ad4a2e965009ee47689a2a9aef838330029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected DebtorsCrate(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DebtorsCrate(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogSetDebtorEventResponse> getLogSetDebtorEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogSetDebtor", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogSetDebtorEventResponse> responses = new ArrayList<LogSetDebtorEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogSetDebtorEventResponse typedResponse = new LogSetDebtorEventResponse();
            typedResponse.debtor = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.debtMoney = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogSetDebtorEventResponse> logSetDebtorEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogSetDebtor", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogSetDebtorEventResponse>() {
            @Override
            public LogSetDebtorEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogSetDebtorEventResponse typedResponse = new LogSetDebtorEventResponse();
                typedResponse.debtor = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.debtMoney = (Uint256) eventValues.getNonIndexedValues().get(0);
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

    public RemoteCall<TransactionReceipt> setDebtor(Address debtor, Uint256 debtMoney) {
        Function function = new Function(
                "setDebtor", 
                Arrays.<Type>asList(debtor, debtMoney), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> manager() {
        Function function = new Function("manager", 
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

    public RemoteCall<TransactionReceipt> changeOwner(Address newOwner) {
        Function function = new Function(
                "changeOwner", 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setManager(Address _manager) {
        Function function = new Function(
                "setManager", 
                Arrays.<Type>asList(_manager), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getDebtor(Address debtor) {
        Function function = new Function("getDebtor", 
                Arrays.<Type>asList(debtor), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<DebtorsCrate> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Address _store, Bytes32 _crate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_store, _crate));
        return deployRemoteCall(DebtorsCrate.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<DebtorsCrate> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Address _store, Bytes32 _crate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_store, _crate));
        return deployRemoteCall(DebtorsCrate.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static DebtorsCrate load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DebtorsCrate(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static DebtorsCrate load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DebtorsCrate(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogSetDebtorEventResponse {
        public Address debtor;

        public Uint256 debtMoney;
    }

    public static class LogChangeOwnerEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
