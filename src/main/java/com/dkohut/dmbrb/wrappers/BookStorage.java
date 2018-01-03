package com.dkohut.dmbrb.wrappers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
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
public class BookStorage extends Contract {
    private static final String BINARY = "0x6060604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610fae806100536000396000f3006060604052600436106100fc576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630e02e5b8146101015780632c6300521461014357806341c0e1b51461017a578063599ebf081461018f5780635c622a0e1461025b5780635f2d813f1461029857806368744046146102da57806384687f7a1461044a5780638da5cb5b14610485578063a1ab9c16146104da578063a6f9dae114610506578063aa6be30314610557578063de8fa431146105a4578063e1e62855146105cd578063e757223014610614578063ee1402361461064b578063f340c0d014610698578063f3f43703146106e5575b600080fd5b341561010c57600080fd5b610141600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610732565b005b341561014e57600080fd5b610164600480803590602001909190505061077a565b6040518082815260200191505060405180910390f35b341561018557600080fd5b61018d6107a4565b005b341561019a57600080fd5b610241600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919080351515906020019091908035906020019091905050610839565b604051808215151515815260200191505060405180910390f35b341561026657600080fd5b61027c60048080359060200190919050506109a0565b604051808260ff1660ff16815260200191505060405180910390f35b34156102a357600080fd5b6102d8600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506109e2565b005b34156102e557600080fd5b6102fb6004808035906020019091905050610a2a565b6040518080602001806020018715151515815260200186815260200185600481111561032357fe5b60ff1681526020018481526020018381038352898181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156103b25780601f10610387576101008083540402835291602001916103b2565b820191906000526020600020905b81548152906001019060200180831161039557829003601f168201915b50508381038252888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156104355780601f1061040a57610100808354040283529160200191610435565b820191906000526020600020905b81548152906001019060200180831161041857829003601f168201915b50509850505050505050505060405180910390f35b341561045557600080fd5b61046b6004808035906020019091905050610a8d565b604051808215151515815260200191505060405180910390f35b341561049057600080fd5b610498610ac4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156104e557600080fd5b6105046004808035906020019091908035906020019091905050610ae9565b005b341561051157600080fd5b61053d600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610b14565b604051808215151515815260200191505060405180910390f35b341561056257600080fd5b61058e600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c98565b6040518082815260200191505060405180910390f35b34156105af57600080fd5b6105b7610cb0565b6040518082815260200191505060405180910390f35b34156105d857600080fd5b6105fa600480803590602001909190803560ff16906020019091905050610cbd565b604051808215151515815260200191505060405180910390f35b341561061f57600080fd5b6106356004808035906020019091905050610d18565b6040518082815260200191505060405180910390f35b341561065657600080fd5b610682600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d42565b6040518082815260200191505060405180910390f35b34156106a357600080fd5b6106cf600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610d8b565b6040518082815260200191505060405180910390f35b34156106f057600080fd5b61071c600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610dd4565b6040518082815260200191505060405180910390f35b80600360008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b600060018281548110151561078b57fe5b9060005260206000209060060201600501549050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156107ff57600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561089657600080fd5b600180548060010182816108aa9190610dec565b9160005260206000209060060201600060c06040519081016040528089815260200188815260200187151581526020018681526020016004808111156108ec57fe5b81526020014281525090919091506000820151816000019080519060200190610916929190610e1e565b506020820151816001019080519060200190610933929190610e1e565b5060408201518160020160006101000a81548160ff0219169083151502179055506060820151816003015560808201518160040160006101000a81548160ff0219169083600481111561098257fe5b021790555060a0820151816005015550505060019050949350505050565b60006001828154811015156109b157fe5b906000526020600020906006020160040160009054906101000a900460ff1660048111156109db57fe5b9050919050565b80600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b600181815481101515610a3957fe5b9060005260206000209060060201600091509050806000019080600101908060020160009054906101000a900460ff16908060030154908060040160009054906101000a900460ff16908060050154905086565b6000600182815481101515610a9e57fe5b906000526020600020906006020160020160009054906101000a900460ff169050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b804201600183815481101515610afb57fe5b9060005260206000209060060201600501819055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610b7157600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515610b9757600080fd5b7f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da26000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b60036020528060005260406000206000915090505481565b6000600180549050905090565b60008160ff166004811115610cce57fe5b600184815481101515610cdd57fe5b906000526020600020906006020160040160006101000a81548160ff02191690836004811115610d0957fe5b02179055506001905092915050565b6000600182815481101515610d2957fe5b9060005260206000209060060201600301549050919050565b6000600360008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60026020528060005260406000206000915090505481565b815481835581811511610e1957600602816006028360005260206000209182019101610e189190610e9e565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e5f57805160ff1916838001178555610e8d565b82800160010185558215610e8d579182015b82811115610e8c578251825591602001919060010190610e71565b5b509050610e9a9190610f15565b5090565b610f1291905b80821115610f0e5760008082016000610ebd9190610f3a565b600182016000610ecd9190610f3a565b6002820160006101000a81549060ff021916905560038201600090556004820160006101000a81549060ff0219169055600582016000905550600601610ea4565b5090565b90565b610f3791905b80821115610f33576000816000905550600101610f1b565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610f605750610f7f565b601f016020900490600052602060002090810190610f7e9190610f15565b5b505600a165627a7a72305820bda152a4546e8271fb060a7451c57fd9369af449d45ec105de6155cfc8afc6960029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected BookStorage(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BookStorage(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeOwnerEventResponse> getLogChangeOwnerEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeOwnerEventResponse> responses = new ArrayList<LogChangeOwnerEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
            typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeOwnerEventResponse> logChangeOwnerEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeOwner", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeOwnerEventResponse>() {
            @Override
            public LogChangeOwnerEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeOwnerEventResponse typedResponse = new LogChangeOwnerEventResponse();
                typedResponse.oldOwner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.newOwner = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> setDebtor(Address _sender, Uint256 amount) {
        Function function = new Function(
                "setDebtor", 
                Arrays.<Type>asList(_sender, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getLastRentDay(Uint256 id) {
        Function function = new Function("getLastRentDay", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        Function function = new Function(
                "kill", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addBook(Utf8String _title, Utf8String _author, Bool _isRentable, Uint256 _price) {
        Function function = new Function(
                "addBook", 
                Arrays.<Type>asList(_title, _author, _isRentable, _price), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> getStatus(Uint256 id) {
        Function function = new Function("getStatus", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setPendingWithdrawals(Address _sender, Uint256 _sum) {
        Function function = new Function(
                "setPendingWithdrawals", 
                Arrays.<Type>asList(_sender, _sum), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>> books(Uint256 param0) {
        final Function function = new Function("books", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>>(
                new Callable<Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>>() {
                    @Override
                    public Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256>(
                                (Utf8String) results.get(0), 
                                (Utf8String) results.get(1), 
                                (Bool) results.get(2), 
                                (Uint256) results.get(3), 
                                (Uint8) results.get(4), 
                                (Uint256) results.get(5));
                    }
                });
    }

    public RemoteCall<Bool> getIsRentable(Uint256 id) {
        Function function = new Function("getIsRentable", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLastRentDay(Uint256 id, Uint256 term) {
        Function function = new Function(
                "setLastRentDay", 
                Arrays.<Type>asList(id, term), 
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

    public RemoteCall<Uint256> debtors(Address param0) {
        Function function = new Function("debtors", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getSize() {
        Function function = new Function("getSize", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> updateBookStatus(Uint256 id, Uint8 _status) {
        Function function = new Function(
                "updateBookStatus", 
                Arrays.<Type>asList(id, _status), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getPrice(Uint256 id) {
        Function function = new Function("getPrice", 
                Arrays.<Type>asList(id), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getDebtor(Address _sender) {
        Function function = new Function("getDebtor", 
                Arrays.<Type>asList(_sender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getPendingWithdrawals(Address _sender) {
        Function function = new Function("getPendingWithdrawals", 
                Arrays.<Type>asList(_sender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> pendingWithdrawals(Address param0) {
        Function function = new Function("pendingWithdrawals", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<BookStorage> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BookStorage.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BookStorage> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BookStorage.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static BookStorage load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BookStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BookStorage load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BookStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeOwnerEventResponse {
        public Address oldOwner;

        public Address newOwner;
    }
}
