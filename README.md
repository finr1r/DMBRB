# DMBRB

## What is this?
DMBRB stands for Decentralized Market for Buying and Renting Books.

## Why this project was created?
The purpose of this project is to demonstrate the possibility of upgrading the smart-contracts written using __Solidity__ language.
For this purpose are used the pattern that separates functionality of the program into three types of smart-contracts:
* Storage - contains the data
* Controller - executes bussiness logic of the program
* Proxy - contain the addresses of the actual version of controller (and maybe storage) contracts

## What tools did I use?
The following tools I did used:
* Java 9 (v9.0.1)
* Gradle (v4.3.1)
* Atom and/or Remix IDE
* Solc (solidity compiler, v0.4.19+commit.c4cbbb05.Linux.g++)
* Truffle (v4.0.3)
* Web3j (v3.2.0)
* Testrpc (v4.1.3)

## What was upgraded?
First of all, storage contracts were fully rewritten. The idea about storage was taken from [ChronoBank](https://github.com/ChronoBank/SmartContracts) project. Now all data stored in __Storage__, but organized in _"crates"_. Access to the data is regulated by __AccessManager__.

Such as all data organized in _"crates"_, the crates-contracts were created for it's managing.

Proxy contract already keeping not only the address of controller but also the addresses of _"crates"_ and __Storage__ contract.

## How to test?
You should have [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html), [Gradle](https://gradle.org/install/), 
[Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) and [Testrpc](https://www.npmjs.com/package/ethereumjs-testrpc).
After installation, you need to clone this repository:
```
git clone https://github.com/finr1r/DMBRB.git
```
And run the tests:
```
gradle test
```

