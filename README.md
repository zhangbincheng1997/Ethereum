# 以太坊开发

## 通过 PPA 安装以太坊
```
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ethereum/ethereum
sudo add-apt-repository -y ppa:ethereum/ethereum-dev
sudo apt-get update
sudo apt-get install ethereum
```

## 通过源码安装以太坊
```
git clone https://github.com/ethereum/go-ethereum
cd go-ethereum
make geth
cd build/bin
ln -s geth /usr/bin/geth
```

## 初始化以太坊
1. 创建工作目录 ethereum
2. 将 genesis.json 创世块文件放入工作目录
2. 执行 geth init genesis.json
```
自动生成 ~/.ethereum
.
├── geth
│   ├── chaindata
│   │   ├── 000001.log
│   │   ├── CURRENT
│   │   ├── LOCK
│   │   ├── LOG
│   │   └── MANIFEST-000000
│   ├── lightchaindata
│   │   ├── 000001.ldb
│   │   ├── CURRENT
│   │   ├── LOCK
│   │   ├── LOG
│   │   └── MANIFEST-000000
└── keystore
```

## 启动以太坊
1. 将 startup.sh 启动脚本放入工作目录
2. 执行 ./start.sh
```
geth --rpc --rpcapi personal,db,eth,net,web3 --networkid 2018 console
help:
--rpc Enable the HTTP-RPC server
--rpcapi API's offered over the HTTP-RPC interface
--networkid 区块链ID-私链
--console 命令行模式
```
2. 开始挖矿
```
miner.start(1) 一个线程挖矿，多线程会很卡
```
3. 停止挖矿
```
miner.stop()
```

## 以太坊钱包
1. 下载地址 https://github.com/ethereum/mist/releases/ 找到对应版本下载。
```
dpkg方式安装:
sudo dpkg -i Ethereum-Wallet-linux64-0-9-3.deb
```
2. Wallets 创建账号，默认首个创建为主账号，挖矿所得的以太币都将进入这个账号，其余为普通账号，可以用来测试。
![alt text](docs/1.png "title")
3. Contracts 发布智能合约，输入 HelloWorld.sol 代码，点击部署。
![alt text](docs/2.png "title")
4. 等待挖矿，智能合约才能确认并且显示出来：
![alt text](docs/3.png "title")

## 在线测试智能合约
1. 智能合约 Solidity 运行在以太坊上的去中心化合约
2. 中文文档 http://www.tryblockchain.org/
3. 英文文档 https://solidity.readthedocs.io/
4. 在线测试 https://remix.ethereum.org/
![alt text](docs/4.png "title")

## Web3j 轻量级的以太坊开发库for Java
1. 依赖说明
```
<dependency>
	<groupId>org.web3j</groupId>
	<artifactId>core</artifactId>
	<version>3.2.0</version>
</dependency>
```
2. 代码说明
```
=> 修改src/main/resources/config.properties里的相关数据
help:
--com.redhat.helloworld.util 工具包
----Consts.java 常量类
--com.redhat.helloworld.test 简单测试
----ClientVersionTest.java 客户端版本
----GenerateWalletTest.java 生成钱包
----TransferEthTest.java 转账
----TransactionGetTest.java Web3j原生调用HelloWorld合约的get方法
----TransactionSetTest.java Web3j原生调用HelloWorld合约的set方法
----FilterTest.java 过滤器
--com.redhat.helloworld.contract 通用框架
----HelloWorldInterface.java HelloWorld合约接口
----HelloWorldContract.java HelloWorld合约实现 继承Web3j提供的Contract类
----HelloWorldMain.java HelloWorld合约测试
```
3. 具体用法
	1. 官方文档 https://web3j.github.io/web3j/
	2. 官方demo1 https://github.com/web3j/sample-project-gradle
	3. 官方demo2 https://github.com/conor10/web3j-javamag

## 其他问题
1. Usage of API documented as @since 1.8+  
设置如下
![alt text](docs/5.png "title")
2. Error:java: Compilation failed: internal java compiler error  
设置如下
![alt text](docs/6.png "title")
