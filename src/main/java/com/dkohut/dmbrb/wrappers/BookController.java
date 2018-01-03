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
public class BookController extends Contract {
    private static final String BINARY = "0x6060604052341561000f57600080fd5b60405160208061325183398101604052808051906020019091905050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008173ffffffffffffffffffffffffffffffffffffffff161415151561009157600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505061316f806100e26000396000f3006060604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630d79b148146100ff57806312ee5057146101545780633ccfd60b1461017657806341c0e1b5146101a35780636ee26566146101b85780638da5cb5b146101e85780639401ca5f1461023d578063a6422d8d1461028e578063a6f9dae1146102c9578063c6661c381461031a578063d43a427614610355575b3373ffffffffffffffffffffffffffffffffffffffff167f8cd787b71532f662d581f9ea765379c7759950cd7dedb590bc8f6f7b454bd5ed346040518082815260200191505060405180910390a2005b341561010a57600080fd5b61011261038e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b61015c6103b4565b604051808215151515815260200191505060405180910390f35b341561018157600080fd5b61018961085a565b604051808215151515815260200191505060405180910390f35b34156101ae57600080fd5b6101b6610a5c565b005b6101ce6004808035906020019091905050610af1565b604051808215151515815260200191505060405180910390f35b34156101f357600080fd5b6101fb6112e5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561024857600080fd5b610274600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061130a565b604051808215151515815260200191505060405180910390f35b341561029957600080fd5b6102af6004808035906020019091905050611490565b604051808215151515815260200191505060405180910390f35b34156102d457600080fd5b610300600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611e5e565b604051808215151515815260200191505060405180910390f35b341561032557600080fd5b61033b6004808035906020019091905050611fe2565b604051808215151515815260200191505060405180910390f35b61037460048080359060200190919080359060200190919050506126ef565b604051808215151515815260200191505060405180910390f35b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000806000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561048157600080fd5b6102c65a03f1151561049257600080fd5b505050604051805190501115156104a857600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561056d57600080fd5b6102c65a03f1151561057e57600080fd5b5050506040518051905092508234101515610762576105a683346130b690919063ffffffff16565b9150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b83360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561066d57600080fd5b6102c65a03f1151561067e57600080fd5b505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561074557600080fd5b6102c65a03f1151561075657600080fd5b50505060019350610854565b61077534846130b690919063ffffffff16565b9050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b833836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561083b57600080fd5b6102c65a03f1151561084c57600080fd5b505050600093505b50505090565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561092257600080fd5b6102c65a03f1151561093357600080fd5b505050604051805190509050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f3360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515610a0457600080fd5b6102c65a03f11515610a1557600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f193505050501515610a5857600080fd5b5090565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610ab757600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b600080600080600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515610bbf57600080fd5b6102c65a03f11515610bd057600080fd5b50505060405180519050141515610be657600080fd5b85610bfb6001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1515610c8957600080fd5b6102c65a03f11515610c9a57600080fd5b5050506040518051905010151515610cb157600080fd5b866000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610d4d57600080fd5b6102c65a03f11515610d5e57600080fd5b5050506040518051905060ff1614158015610e2f575060018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610e0e57600080fd5b6102c65a03f11515610e1f57600080fd5b5050506040518051905060ff1614155b1515610e3a57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e7572230896000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515610ed357600080fd5b6102c65a03f11515610ee457600080fd5b505050604051805190509550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515610fb557600080fd5b6102c65a03f11515610fc657600080fd5b505050604051805190509450610fe585346130cf90919063ffffffff16565b935085841015156111fd57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e1e628558960016000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b151561109557600080fd5b6102c65a03f115156110a657600080fd5b50505060405180519050506110c486856130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561118a57600080fd5b6102c65a03f1151561119b57600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167f721150c954b3b49b564e00c2ab497687c9bb8d7443e5075169ca242589a8be718988604051808381526020018281526020019250505060405180910390a2600196506112da565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b15156112c157600080fd5b6102c65a03f115156112d257600080fd5b505050600096505b505050505050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561136757600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff161415151561138d57600080fd5b7f83165188de0ad8548c344fed77466c8ed1821a3db46e720a894a1706cc6eb07f600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a181600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b6000806000806000806000876114b06001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561153e57600080fd5b6102c65a03f1151561154f57600080fd5b505050604051805190501015151561156657600080fd5b886000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561160257600080fd5b6102c65a03f1151561161357600080fd5b5050506040518051905060ff1614151561162c57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15156116c557600080fd5b6102c65a03f115156116d657600080fd5b5050506040518051905042101515156116ee57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e1e628558b60026000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b151561179357600080fd5b6102c65a03f115156117a457600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561184857600080fd5b6102c65a03f1151561185957600080fd5b50505060405180519050975061188d6201518061187f8a426130b690919063ffffffff16565b6130ed90919063ffffffff16565b96506000871115611df757611971876119636096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308f6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561193a57600080fd5b6102c65a03f1151561194b57600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515611a3857600080fd5b6102c65a03f11515611a4957600080fd5b5050506040518051905094508585101515611b4d57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33611ab089896130b690919063ffffffff16565b6040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515611b3457600080fd5b6102c65a03f11515611b4557600080fd5b505050611df6565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515611c1257600080fd5b6102c65a03f11515611c2357600080fd5b505050604051805190509350611c4284876130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f3360006040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515611d0957600080fd5b6102c65a03f11515611d1a57600080fd5b505050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630e02e5b833856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515611de157600080fd5b6102c65a03f11515611df257600080fd5b5050505b5b3373ffffffffffffffffffffffffffffffffffffffff167fe102540a3ae5650ad44bf7399fdd9b4a581c282badb8975a70665f8a9ce186108b42604051808381526020018281526020019250505060405180910390a2600198505050505050505050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515611ebb57600080fd5b60008273ffffffffffffffffffffffffffffffffffffffff1614151515611ee157600080fd5b7f96b36bedce75759b139551b10b3d2e1e863dbbfbdc30f9f9e374bb24431d5da26000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1683604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019250505060405180910390a1816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060019050919050565b600080600080600080866120006001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561208e57600080fd5b6102c65a03f1151561209f57600080fd5b50505060405180519050101515156120b657600080fd5b876000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561215257600080fd5b6102c65a03f1151561216357600080fd5b5050506040518051905060ff1614151561217c57600080fd5b42600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561221657600080fd5b6102c65a03f1151561222757600080fd5b505050604051805190501015151561223e57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e1e628558a60036000604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b15156122e357600080fd5b6102c65a03f115156122f457600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632c6300528a6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561239857600080fd5b6102c65a03f115156123a957600080fd5b5050506040518051905096506123dd620151806123cf428a6130b690919063ffffffff16565b6130ed90919063ffffffff16565b95506124b8866124aa6096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308e6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561248157600080fd5b6102c65a03f1151561249257600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9450600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b151561257f57600080fd5b6102c65a03f1151561259057600080fd5b5050506040518051905093506125af85856130cf90919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561267557600080fd5b6102c65a03f1151561268657600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167fe102540a3ae5650ad44bf7399fdd9b4a581c282badb8975a70665f8a9ce186108a42604051808381526020018281526020019250505060405180910390a26001975050505050505050919050565b6000806000806000806000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663ee140236336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b15156127bf57600080fd5b6102c65a03f115156127d057600080fd5b505050604051805190501415156127e657600080fd5b876127fb6001826130cf90919063ffffffff16565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663de8fa4316000604051602001526040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b151561288957600080fd5b6102c65a03f1151561289a57600080fd5b50505060405180519050101515156128b157600080fd5b886000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b151561294d57600080fd5b6102c65a03f1151561295e57600080fd5b5050506040518051905060ff1614158015612a2f575060018060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635c622a0e836000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515612a0e57600080fd5b6102c65a03f11515612a1f57600080fd5b5050506040518051905060ff1614155b1515612a3a57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166384687f7a8b6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515612ad357600080fd5b6102c65a03f11515612ae457600080fd5b505050604051805190501515612af957600080fd5b612b226001612b14620151808c6130ed90919063ffffffff16565b6130cf90919063ffffffff16565b9650600e8710151515612b3157fe5b612c0a87612bfc6096600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e75722308f6000604051602001526040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1515612bd357600080fd5b6102c65a03f11515612be457600080fd5b505050604051805190506130ed90919063ffffffff16565b61310890919063ffffffff16565b9550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663f340c0d0336000604051602001526040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1515612cd157600080fd5b6102c65a03f11515612ce257600080fd5b505050604051805190509450612d0185346130cf90919063ffffffff16565b93508584101515612fcc57600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e1e628558b600080604051602001526040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808381526020018260ff16815260200192505050602060405180830381600087803b1515612db057600080fd5b6102c65a03f11515612dc157600080fd5b5050506040518051905050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a1ab9c168b8b6040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083815260200182815260200192505050600060405180830381600087803b1515612e6457600080fd5b6102c65a03f11515612e7557600080fd5b505050612e8b86856130b690919063ffffffff16565b9250600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33856040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b1515612f5157600080fd5b6102c65a03f11515612f6257600080fd5b5050503373ffffffffffffffffffffffffffffffffffffffff167f675370f7c886afde46bc0fa10a6de18eb8d2111b22ef2186b6910bef8f8d96fa8b888a60405180848152602001838152602001828152602001935050505060405180910390a2600197506130a9565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16635f2d813f33866040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b151561309057600080fd5b6102c65a03f115156130a157600080fd5b505050600097505b5050505050505092915050565b60008282111515156130c457fe5b818303905092915050565b60008082840190508381101515156130e357fe5b8091505092915050565b60008082848115156130fb57fe5b0490508091505092915050565b600080600084141561311d576000915061313c565b828402905082848281151561312e57fe5b0414151561313857fe5b8091505b50929150505600a165627a7a7230582078ca66d4a683df889f41426d84903598b2bb92df90a87ded3abf9980b743fb8c0029";

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<>();
    }

    protected BookController(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BookController(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LogChangeBookStorageEventResponse> getLogChangeBookStorageEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogChangeBookStorage", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogChangeBookStorageEventResponse> responses = new ArrayList<LogChangeBookStorageEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogChangeBookStorageEventResponse typedResponse = new LogChangeBookStorageEventResponse();
            typedResponse._oldBookStorage = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse._newBookStorage = (Address) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogChangeBookStorageEventResponse> logChangeBookStorageEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogChangeBookStorage", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogChangeBookStorageEventResponse>() {
            @Override
            public LogChangeBookStorageEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogChangeBookStorageEventResponse typedResponse = new LogChangeBookStorageEventResponse();
                typedResponse._oldBookStorage = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse._newBookStorage = (Address) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogBuyBookEventResponse> getLogBuyBookEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogBuyBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogBuyBookEventResponse> responses = new ArrayList<LogBuyBookEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogBuyBookEventResponse typedResponse = new LogBuyBookEventResponse();
            typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogBuyBookEventResponse> logBuyBookEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogBuyBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogBuyBookEventResponse>() {
            @Override
            public LogBuyBookEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogBuyBookEventResponse typedResponse = new LogBuyBookEventResponse();
                typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogRentBookEventResponse> getLogRentBookEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogRentBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogRentBookEventResponse> responses = new ArrayList<LogRentBookEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogRentBookEventResponse typedResponse = new LogRentBookEventResponse();
            typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._term = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogRentBookEventResponse> logRentBookEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogRentBook", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogRentBookEventResponse>() {
            @Override
            public LogRentBookEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogRentBookEventResponse typedResponse = new LogRentBookEventResponse();
                typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._price = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._term = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<LogBookReturnEventResponse> getLogBookReturnEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogBookReturn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogBookReturnEventResponse> responses = new ArrayList<LogBookReturnEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogBookReturnEventResponse typedResponse = new LogBookReturnEventResponse();
            typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._time = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogBookReturnEventResponse> logBookReturnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogBookReturn", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogBookReturnEventResponse>() {
            @Override
            public LogBookReturnEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogBookReturnEventResponse typedResponse = new LogBookReturnEventResponse();
                typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._id = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._time = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<LogEtherReceivedEventResponse> getLogEtherReceivedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LogEtherReceived", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LogEtherReceivedEventResponse> responses = new ArrayList<LogEtherReceivedEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LogEtherReceivedEventResponse typedResponse = new LogEtherReceivedEventResponse();
            typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LogEtherReceivedEventResponse> logEtherReceivedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LogEtherReceived", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LogEtherReceivedEventResponse>() {
            @Override
            public LogEtherReceivedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LogEtherReceivedEventResponse typedResponse = new LogEtherReceivedEventResponse();
                typedResponse._sender = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
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

    public RemoteCall<Address> bookStorage() {
        Function function = new Function("bookStorage", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> payDebt(BigInteger weiValue) {
        Function function = new Function(
                "payDebt", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        Function function = new Function(
                "withdraw", 
                Arrays.<Type>asList(), 
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

    public RemoteCall<TransactionReceipt> buyBook(Uint256 id, BigInteger weiValue) {
        Function function = new Function(
                "buyBook", 
                Arrays.<Type>asList(id), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Address> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> changeBookStorage(Address _bookStorage) {
        Function function = new Function(
                "changeBookStorage", 
                Arrays.<Type>asList(_bookStorage), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> bookReturn(Uint256 id) {
        Function function = new Function(
                "bookReturn", 
                Arrays.<Type>asList(id), 
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

    public RemoteCall<TransactionReceipt> bookEarlyReturn(Uint256 id) {
        Function function = new Function(
                "bookEarlyReturn", 
                Arrays.<Type>asList(id), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> rentBook(Uint256 id, Uint256 term, BigInteger weiValue) {
        Function function = new Function(
                "rentBook", 
                Arrays.<Type>asList(id, term), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<BookController> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, Address _bookStorage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_bookStorage));
        return deployRemoteCall(BookController.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<BookController> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, Address _bookStorage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(_bookStorage));
        return deployRemoteCall(BookController.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static BookController load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BookController(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BookController load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BookController(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class LogChangeBookStorageEventResponse {
        public Address _oldBookStorage;

        public Address _newBookStorage;
    }

    public static class LogBuyBookEventResponse {
        public Address _sender;

        public Uint256 _id;

        public Uint256 _price;
    }

    public static class LogRentBookEventResponse {
        public Address _sender;

        public Uint256 _id;

        public Uint256 _price;

        public Uint256 _term;
    }

    public static class LogBookReturnEventResponse {
        public Address _sender;

        public Uint256 _id;

        public Uint256 _time;
    }

    public static class LogEtherReceivedEventResponse {
        public Address _sender;

        public Uint256 _value;
    }

    public static class LogChangeOwnerEventResponse {
        public Address oldOwner;

        public Address newOwner;
    }
}
