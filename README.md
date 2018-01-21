# 以太坊开发

## 安装以太坊
1. 通过 ppa 安装(推荐)
```
sudo apt-get install software-properties-common
sudo add-apt-repository -y ppa:ethereum/ethereum
sudo apt-get update
sudo apt-get install ethereum
```
error1- gpg: no valid OpenPGP data found:  
```
# 找不到有效的公钥
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys XXXXXXXXXXXXXXXX(例如: 1C52189C923F6CA9)
```
error2- E: 无法下载 http://cn.archive.ubuntu.com/ubuntu/pool/universe/s/swarm-cluster/swarm_2.1.8-2_amd64.deb  404  Not Found [IP: 2001:67c:1562::16 80]
  ```
# 找不到有效的镜像
sudo vim /etc/apt/sources.list
添加两行
deb http://ppa.launchpad.net/ethereum/ethereum/ubuntu artful main
deb-src http://ppa.launchpad.net/ethereum/ethereum/ubuntu artful main
```
2. 通过 source 安装
```
git clone https://github.com/ethereum/go-ethereum
sudo apt-get install -y build-essential golang
cd go-ethereum
make geth
ln -s build/bin/geth /usr/bin/geth # 软链接
```
3. 更多安装方式请看
```
https://github.com/ethereum/go-ethereum/wiki/Building-Ethereum
```

## 初始以太坊
1. 创建工作目录 ethereum
2. 将 genesis.json 创世文件放入工作目录
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
│   └── lightchaindata
│       ├── 000001.log
│       ├── CURRENT
│       ├── LOCK
│       ├── LOG
│       └── MANIFEST-000000
├── history
└── keystore
```

## 启动以太坊
1. 将 startup.sh 启动脚本放入工作目录
2. 执行 ./startup.sh
```
geth --rpc --rpcapi personal,db,eth,net,web3 --networkid 666666 console
help:
--rpc Enable the HTTP-RPC server
--rpcapi API's offered over the HTTP-RPC interface
--networkid 区块链ID-私链
--console 命令行模式
```
3. 命令行创建钱包
```
# 查询用户
eth.accounts
# 创建用户
personal.newAccount("password")
# 查询余额
eth.getBalance(eth.accounts[0])
```
4. 图形化创建钱包  
```
# 下载地址 https://github.com/ethereum/mist/releases/
sudo dpkg -i Ethereum-Wallet-linux64-0-9-3.deb
```
![alt text](docs/1.png "title")
5. 开始挖矿
```
miner.start(1) # 一个线程挖矿，多线程会很卡
```
6. 停止挖矿
```
miner.stop()
```

## 部署智能合约
1. wallet
![alt text](docs/2.png "title")
![alt text](docs/3.png "title")
2. geth
![alt text](docs/4.png "title")
![alt text](docs/5.png "title")
```
# 解锁用户
personal.unlockAccount(eth.account[0])
# 复制 https://remix.ethereum.org/ Compile - Details - WEB3DEPLOY 到 geth
var helloworldContract = web3.eth.contract(......);
var helloworld = helloworldContract.new(......)
```
3. web3j
``` com.redhat.helloworld.contract
/**
 * 部署合约
 *
 * @param web3j       RPC请求
 * @param credentials 钱包凭证
 * @param gasPrice    GAS价格
 * @param gasLimit    GAS上限
 * @return
 */
public static RemoteCall<HelloWorldContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
    // 构造函数参数 NULL
    String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
    return deployRemoteCall(HelloWorldContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
}
```
> 参考链接
  1. 中文文档 http://www.tryblockchain.org/
  2. 英文文档 https://solidity.readthedocs.io/
  3. 在线测试 https://remix.ethereum.org/

## Web3j 轻量级的以太坊开发库for Java
1. 依赖
```
<dependencies>
	<dependency>
		<groupId>org.web3j</groupId>
		<artifactId>core</artifactId>
		<version>3.2.0</version>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-log4j12</artifactId>
		<version>1.7.25</version>
	</dependency>
</dependencies>
```
2. 结构
```
note:
src/main/resources/config.properties 配置文件
help:
--com.redhat.helloworld.util 工具包
----Consts.java 常量类
--com.redhat.helloworld.test 简单测试
----ClientVersionTest.java 客户端版本
----GenerateWalletTest.java 生成钱包
----TransferEthTest.java 转账
----TransactionGetTest.java Web3j 原生调用HelloWorld 合约的 get 方法
----TransactionSetTest.java Web3j 原生调用HelloWorld 合约的 set 方法
----FilterTest.java 过滤器
--com.redhat.helloworld.contract 通用框架
----HelloWorldInterface.java HelloWorld 合约接口
----HelloWorldContract.java HelloWorld 合约实现 !!!继承 Web3j 提供的 Contract 类!!!
----HelloWorldMain.java HelloWorld 合约测试
```

## 最佳实践
```
继承 Web3j 提供的 Contract 类，里面封装了许多函数:
// 请求返回值为单个的函数
protected <T extends Type> RemoteCall<T> executeRemoteCallSingleValueReturn(Function function) {
	return new RemoteCall<>(() -> executeCallSingleValueReturn(function));
}
// 请求返回值为多个的函数
protected RemoteCall<List<Type>> executeRemoteCallMultipleValueReturn(Function function) {
	return new RemoteCall<>(() -> executeCallMultipleValueReturn(function));
}
// 请求执行事务的函数
protected RemoteCall<TransactionReceipt> executeRemoteCallTransaction(Function function) {
	return new RemoteCall<>(() -> executeTransaction(function));
}
用法请看com.redhat.helloworld.contract.HelloWorldContract.java:
public class HelloWorldContract extends Contract implements HelloWorldInterface {
......
}
```
> 参考链接
  1. 官方文档 https://web3j.github.io/web3j/
  2. 官方demo1 https://github.com/web3j/sample-project-gradle
  3. 官方demo2 https://github.com/conor10/web3j-javamag

## 错误信息
1. Usage of API documented as @since 1.8+  
解决方法设置如下:
![alt text](docs/6.png "title")
2. Error:java: Compilation failed: internal java compiler error  
解决方法设置如下:
![alt text](docs/7.png "title")
3. 以上错误可以通过 Maven 设置编译器级别解决
```
<properties>
	<maven.compiler.source>1.8</maven.compiler.source>
	<maven.compiler.target>1.8</maven.compiler.target>
</properties>
```
