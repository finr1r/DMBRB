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
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
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
public class BooksCrate extends Contract {
    private static final String BINARY = "0x60606040527f436f6e74726f6c6c65720000000000000000000000000000000000000000000060019060001916905534156200003a57600080fd5b60405160408062002467833981016040528080519060200190919080519060200190919050508181336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060010260001916816000191614151515620000bb57fe5b620000e182826003620001736401000000000262001b7417909291906401000000009004565b5050620001277f626f6f6b730000000000000000000000000000000000000000000000000000006005620001c86401000000000262001bc9179091906401000000009004565b6200016b7f706572696f64734f66426f6f6b52656e74000000000000000000000000000000600b6200043c6401000000000262001db9179091906401000000009004565b50506200059a565b818360000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080836001018160001916905550505050565b620002308260000182604051808260001916600019168152602001807f7469746c650000000000000000000000000000000000000000000000000000008152506005019150506040518091039020620004636401000000000262001dca176401000000009004565b620002988260010182604051808260001916600019168152602001807f617574686f7200000000000000000000000000000000000000000000000000008152506006019150506040518091039020620004636401000000000262001dca176401000000009004565b620003008260020182604051808260001916600019168152602001807f697352656e7465640000000000000000000000000000000000000000000000008152506008019150506040518091039020620004986401000000000262001de9176401000000009004565b620003688260030182604051808260001916600019168152602001807f70726963650000000000000000000000000000000000000000000000000000008152506005019150506040518091039020620004cd6401000000000262001e08176401000000009004565b620003d08260040182604051808260001916600019168152602001807f73746174757300000000000000000000000000000000000000000000000000008152506006019150506040518091039020620004cd6401000000000262001e08176401000000009004565b620004388260050182604051808260001916600019168152602001807f6f776e65720000000000000000000000000000000000000000000000000000008152506005019150506040518091039020620005026401000000000262001e27176401000000009004565b5050565b6200045f8260000182620005376401000000000262001e46176401000000009004565b5050565b620004878260000154826200056c6401000000000262001e65176401000000009004565b808260000181600019169055505050565b620004bc8260000154826200056c6401000000000262001e65176401000000009004565b808260000181600019169055505050565b620004f18260000154826200056c6401000000000262001e65176401000000009004565b808260000181600019169055505050565b620005268260000154826200056c6401000000000262001e65176401000000009004565b808260000181600019169055505050565b6200055b8260000154826200056c6401000000000262001e65176401000000009004565b808260000181600019169055505050565b600060010282600019161415806200058b575060006001028160001916145b156200059657600080fd5b5050565b611ebd80620005aa6000396000f3006060604052600436106100f1576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063218ae47e146101015780633ae592061461015f57806341c0e1b5146101c6578063481c6a75146101db5780635d105172146102305780636f137e2214610273578063792aac95146102b25780638da5cb5b146102ed578063a547b53c14610342578063a6f9dae1146103af578063ad1a7d0c14610400578063b08e9692146104d0578063c15592481461051b578063c1df4e8f14610556578063c2b025eb1461059e578063cec4056b146105d9578063d0ebdbe71461061c575b34156100fc57600080fd5b600080fd5b341561010c57600080fd5b61014560048080356000191690602001909190803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061066d565b604051808215151515815260200191505060405180910390f35b341561016a57600080fd5b6101846004808035600019169060200190919050506107b9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156101d157600080fd5b6101d96107db565b005b34156101e657600080fd5b6101ee610870565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561023b57600080fd5b610255600480803560001916906020019091905050610896565b60405180826000191660001916815260200191505060405180910390f35b341561027e57600080fd5b6102986004808035600019169060200190919050506108b9565b604051808215151515815260200191505060405180910390f35b34156102bd57600080fd5b6102d76004808035600019169060200190919050506108dc565b6040518082815260200191505060405180910390f35b34156102f857600080fd5b6103006108fc565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561034d57600080fd5b61039560048080356000191690602001909190803560001916906020019091908035600019169060200190919080351515906020019091908035906020019091905050610921565b604051808215151515815260200191505060405180910390f35b34156103ba57600080fd5b6103e6600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610a6f565b604051808215151515815260200191505060405180910390f35b341561040b57600080fd5b610425600480803560001916906020019091905050610bb6565b6040518085600260200280838360005b83811015610450578082015181840152602081019050610435565b505050509050018415151515815260200183600260200280838360005b8381101561048857808201518184015260208101905061046d565b505050509050018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200194505050505060405180910390f35b34156104db57600080fd5b61050160048080356000191690602001909190803560ff16906020019091905050610bf0565b604051808215151515815260200191505060405180910390f35b341561052657600080fd5b610540600480803560001916906020019091905050610d20565b6040518082815260200191505060405180910390f35b341561056157600080fd5b610584600480803560001916906020019091908035906020019091905050610d43565b604051808215151515815260200191505060405180910390f35b34156105a957600080fd5b6105c3600480803560001916906020019091905050610e65565b6040518082815260200191505060405180910390f35b34156105e457600080fd5b6105fe600480803560001916906020019091905050610e88565b60405180826000191660001916815260200191505060405180910390f35b341561062757600080fd5b610653600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610eab565b604051808215151515815260200191505060405180910390f35b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd09336001546000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b151561074657600080fd5b6102c65a03f1151561075757600080fd5b50505060405180519050151561076c57600080fd5b8160008173ffffffffffffffffffffffffffffffffffffffff161415151561079357600080fd5b6107ae6005800185856003610f7a909392919063ffffffff16565b600191505092915050565b60006107d4600580018360036110a79092919063ffffffff16565b9050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561083657600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60006108b260056000018360036111b59092919063ffffffff16565b9050919050565b60006108d560056002018360036112c39092919063ffffffff16565b9050919050565b60006108f5600b8360036113d19092919063ffffffff16565b9050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061093f6005600001878760036113ee909392919063ffffffff16565b61095b6005600101878660036113ee909392919063ffffffff16565b6109776005600201878560036114f7909392919063ffffffff16565b6109936005600301878460036115fc909392919063ffffffff16565b6109ba6005600401876004808111156109a857fe5b60036115fc909392919063ffffffff16565b6109d56005800187336003610f7a909392919063ffffffff16565b3373ffffffffffffffffffffffffffffffffffffffff167fcc574f4ce3f5b3a3de1c220e9eb3c947c71a9044662c36f898c2565b2c8b4a0086868686600480811115610a1d57fe5b6040518086600019166000191681526020018560001916600019168152602001841515151581526020018381526020018281526020019550505050505060405180910390a26001905095945050505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610acc57600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515610af257600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da260405160405180910390a3816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b610bbe611b23565b6000610bc8611b4d565b6000610be160058660036116fd9092919063ffffffff16565b93509350935093509193509193565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd09336001546000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b1515610cc957600080fd5b6102c65a03f11515610cda57600080fd5b505050604051805190501515610cef57600080fd5b610d16600560040184846004811115610d0457fe5b60036115fc909392919063ffffffff16565b6001905092915050565b6000610d3c60056004018360036117e69092919063ffffffff16565b9050919050565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635cb8dd09336001546000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001826000191660001916815260200192505050602060405180830381600087803b1515610e1c57600080fd5b6102c65a03f11515610e2d57600080fd5b505050604051805190501515610e4257600080fd5b610e5b600b848460036118f4909392919063ffffffff16565b6001905092915050565b6000610e8160056003018360036117e69092919063ffffffff16565b9050919050565b6000610ea460056001018360036111b59092919063ffffffff16565b9050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610f0857600080fd5b8160008173ffffffffffffffffffffffffffffffffffffffff1614151515610f2f57600080fd5b82600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001915050919050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166379a7cfee85600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180846000191660001916815260200183600019166000191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019350505050600060405180830381600087803b151561108d57600080fd5b6102c65a03f1151561109e57600080fd5b50505050505050565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663d3a39686856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b151561119157600080fd5b6102c65a03f115156111a257600080fd5b5050506040518051905090509392505050565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166337d9d7fc856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b151561129f57600080fd5b6102c65a03f115156112b057600080fd5b5050506040518051905090509392505050565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166398eaf11c856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b15156113ad57600080fd5b6102c65a03f115156113be57600080fd5b5050506040518051905090509392505050565b60006113e184846000018461190c565b6001900490509392505050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a14ff85785600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808460001916600019168152602001836000191660001916815260200182600019166000191681526020019350505050600060405180830381600087803b15156114dd57600080fd5b6102c65a03f115156114ee57600080fd5b50505050505050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f0f53ee485600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018084600019166000191681526020018360001916600019168152602001821515151581526020019350505050600060405180830381600087803b15156115e257600080fd5b6102c65a03f115156115f357600080fd5b50505050505050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166353e7168b85600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180846000191660001916815260200183600019166000191681526020018281526020019350505050600060405180830381600087803b15156116e357600080fd5b6102c65a03f115156116f457600080fd5b50505050505050565b611705611b23565b600061170f611b4d565b600061171f8787600001876111b5565b84600060028110151561172e57fe5b6020020190600019169081600019168152505061174f8787600101876111b5565b84600160028110151561175e57fe5b6020020190600019169081600019168152505061177f8787600201876112c3565b925061178f8787600301876117e6565b82600060028110151561179e57fe5b6020020181815250506117b58787600401876117e6565b8260016002811015156117c457fe5b6020020181815250506117db8787600501876110a7565b905093509350935093565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166353aa3f5e856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b15156118d057600080fd5b6102c65a03f115156118e157600080fd5b5050506040518051905090509392505050565b61190684846000018484600102611a1a565b50505050565b60008360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166337d9d7fc856001015485600001548560405180836000191660001916815260200182600019166000191681526020019250505060405180910390206000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360001916600019168152602001826000191660001916815260200192505050602060405180830381600087803b15156119f657600080fd5b6102c65a03f11515611a0757600080fd5b5050506040518051905090509392505050565b8360000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a14ff85785600101548560000154856040518083600019166000191681526020018260001916600019168152602001925050506040518091039020846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808460001916600019168152602001836000191660001916815260200182600019166000191681526020019350505050600060405180830381600087803b1515611b0957600080fd5b6102c65a03f11515611b1a57600080fd5b50505050505050565b60408051908101604052806002905b6000801916815260200190600190039081611b325790505090565b60408051908101604052806002905b6000815260200190600190039081611b5c5790505090565b818360000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080836001018160001916905550505050565b611c1b8260000182604051808260001916600019168152602001807f7469746c650000000000000000000000000000000000000000000000000000008152506005019150506040518091039020611dca565b611c6d8260010182604051808260001916600019168152602001807f617574686f7200000000000000000000000000000000000000000000000000008152506006019150506040518091039020611dca565b611cbf8260020182604051808260001916600019168152602001807f697352656e7465640000000000000000000000000000000000000000000000008152506008019150506040518091039020611de9565b611d118260030182604051808260001916600019168152602001807f70726963650000000000000000000000000000000000000000000000000000008152506005019150506040518091039020611e08565b611d638260040182604051808260001916600019168152602001807f73746174757300000000000000000000000000000000000000000000000000008152506006019150506040518091039020611e08565b611db58260050182604051808260001916600019168152602001807f6f776e65720000000000000000000000000000000000000000000000000000008152506005019150506040518091039020611e27565b5050565b611dc68260000182611e46565b5050565b611dd8826000015482611e65565b808260000181600019169055505050565b611df7826000015482611e65565b808260000181600019169055505050565b611e16826000015482611e65565b808260000181600019169055505050565b611e35826000015482611e65565b808260000181600019169055505050565b611e54826000015482611e65565b808260000181600019169055505050565b60006001028260001916141580611e83575060006001028160001916145b15611e8d57600080fd5b50505600a165627a7a723058203e28f0785d9d633bb429dbe13dea77a0854686df51e72c5d8b0360d9b7188f870029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected BooksCrate(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BooksCrate(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogAddBookEventResponse> getLogAddBookEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogAddBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogAddBookEventResponse> responses = new ArrayList<LogAddBookEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogAddBookEventResponse typedResponse = new LogAddBookEventResponse();
            typedResponse.seller = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.title = (Bytes32) eventValues.getNonIndexedValues().get(0);
            typedResponse.author = (Bytes32) eventValues.getNonIndexedValues().get(1);
            typedResponse.isRentable = (Bool) eventValues.getNonIndexedValues().get(2);
            typedResponse.price = (Uint256) eventValues.getNonIndexedValues().get(3);
            typedResponse.status = (Uint256) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogAddBookEventResponse> logAddBookEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogAddBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogAddBookEventResponse>() {
            @Override
            public LogAddBookEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogAddBookEventResponse typedResponse = new LogAddBookEventResponse();
                typedResponse.seller = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.title = (Bytes32) eventValues.getNonIndexedValues().get(0);
                typedResponse.author = (Bytes32) eventValues.getNonIndexedValues().get(1);
                typedResponse.isRentable = (Bool) eventValues.getNonIndexedValues().get(2);
                typedResponse.price = (Uint256) eventValues.getNonIndexedValues().get(3);
                typedResponse.status = (Uint256) eventValues.getNonIndexedValues().get(4);
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

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> addBook(Bytes32 _key, Bytes32 title, Bytes32 author, Bool isRentable, Uint256 price) {
        Function function = new Function(
                "addBook", 
                Arrays.<Type>asList(_key, title, author, isRentable, price), 
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

    public RemoteCall<TransactionReceipt> setManager(Address _manager) {
        Function function = new Function(
                "setManager", 
                Arrays.<Type>asList(_manager), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<BooksCrate> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Address _store, Bytes32 _crate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_store, _crate));
        return deployRemoteCall(BooksCrate.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<BooksCrate> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Address _store, Bytes32 _crate) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_store, _crate));
        return deployRemoteCall(BooksCrate.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static BooksCrate load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BooksCrate(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BooksCrate load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BooksCrate(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogAddBookEventResponse {
        public Address seller;

        public Bytes32 title;

        public Bytes32 author;

        public Bool isRentable;

        public Uint256 price;

        public Uint256 status;
    }

    public static class LogChangeOwnerEventResponse {
        public Address previousOwner;

        public Address newOwner;
    }
}
