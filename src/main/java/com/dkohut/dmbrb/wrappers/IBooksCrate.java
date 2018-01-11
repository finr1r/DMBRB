package com.dkohut.dmbrb.wrappers;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class IBooksCrate extends Contract {
    private static final String BINARY = "0x";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected IBooksCrate(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IBooksCrate(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setBookOwner(Bytes32 _key, Address newOwner) {
        Function function = new Function(
                "setBookOwner", 
                Arrays.<Type>asList(_key, newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getBookOwner(Bytes32 _key) {
        Function function = new Function("getBookOwner", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bytes32> getBookTitle(Bytes32 _key) {
        Function function = new Function("getBookTitle", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> getBookIsRentable(Bytes32 _key) {
        Function function = new Function("getBookIsRentable", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getPeriodOfBookRent(Bytes32 _key) {
        Function function = new Function("getPeriodOfBookRent", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> addBook(Bytes32 _key, Bytes32 title, Bytes32 author, Bool isRentable, Uint256 price) {
        Function function = new Function(
                "addBook", 
                Arrays.<Type>asList(_key, title, author, isRentable, price), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address>> getBook(Bytes32 _key) {
        final Function function = new Function("getBook", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<StaticArray2<Bytes32>>() {}, new TypeReference<Bool>() {}, new TypeReference<StaticArray2<Uint256>>() {}, new TypeReference<Address>() {}));
        return new RemoteCall<Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address>>(
                new Callable<Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address>>() {
                    @Override
                    public Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address>(
                                (StaticArray2<Bytes32>) results.get(0), 
                                (Bool) results.get(1), 
                                (StaticArray2<Uint256>) results.get(2), 
                                (Address) results.get(3));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> setBookStatus(Bytes32 _key, Uint8 status) {
        Function function = new Function(
                "setBookStatus", 
                Arrays.<Type>asList(_key, status), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getBookStatus(Bytes32 _key) {
        Function function = new Function("getBookStatus", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setPeriodOfBookRent(Bytes32 _key, Uint256 period) {
        Function function = new Function(
                "setPeriodOfBookRent", 
                Arrays.<Type>asList(_key, period), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getBookPrice(Bytes32 _key) {
        Function function = new Function("getBookPrice", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bytes32> getBookAuthor(Bytes32 _key) {
        Function function = new Function("getBookAuthor", 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public static RemoteCall<IBooksCrate> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBooksCrate.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IBooksCrate> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IBooksCrate.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static IBooksCrate load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBooksCrate(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static IBooksCrate load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IBooksCrate(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }
}
