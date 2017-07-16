# 以太坊开发

## Geth以太坊客户端
> * 1、下载地址https://geth.ethereum.org/downloads/ 貌似我打不开的样子？
> * 2、将创世块文件genesis.json复制到到Geth文件夹下。
> * 3、第一次启动Geth，需要初始化创世块：
```
geth -datadir "%cd%\chain" init genesis.json
```

> * 4、启动Geth，可将命令放在startup.bat脚本，双击脚本启动：
```
geth --identity "ethnode" --rpc --rpccorsdomain "*" --datadir "%cd%\chain" --port "30303" --rpcapi "db,eth,net,web3" --networkid 666666 console

identity
# 区块链标识
rpc
# rpc启动通信
rpccorsdomain
# rpc跨源请求
datadir
# 区块链数据位置
port
# 监听端口
rpcapi
# 允许接口
networkid
# 区块链ID
console
# 命令行模式
```

## Ethereum Wallet钱包客户端
> * 1、下载地址https://github.com/ethereum/mist/releases/ 找到对应版本下载。
> * 2、在Wallets处创建账号，默认首个创建为主账号，挖矿所得的以太币都将进入这个账号，其余为普通账号，可以用来测试。
> * 3、在Contracts处发布智能合约，输入HelloWorld.sol代码，点击部署。
4、这个时候看不到部署的智能合约，需要切换到Geth进行挖矿，挖到一定数量的矿之后，智能合约才能确认并且显示出来：
```
miner.start(1)
# 开启一个线程挖矿，多线程会很卡
miner.stop()
# 关闭挖矿命令
```

## Web3j 轻量级的以太坊开发库for Java
> * 1、Maven依赖
```
<dependency>
	<groupId>org.web3j</groupId>
	<artifactId>core</artifactId>
	<version>2.2.2</version>
</dependency>
```

> * 2、代码说明
```
--com.redhat.helloworld.util
----Consts.java 常量类 需要修改PASSWORD、PATH和HELLOWORLD_CONTRACT_ADDRESS
----Util.java 工具类

--com.redhat.helloworld.test
----ClientVersionTest.java 客户端版本
----GenerateWalletTest.java 生成钱包
----TransferEthTest.java 转账
----TransactionGetTest.java Web3j原生调用HelloWorld合约的get方法
----TransactionSetTest.java Web3j原生调用HelloWorld合约的set方法
----FilterTest.java 过滤器

--com.redhat.helloworld.contract
----HelloWorldInterface.java HelloWorld合约接口
----HelloWorldContract.java HelloWorld合约实现 继承Web3j提供的Contract类
----HelloWorldTest.java HelloWorld合约测试
```

> * 3、具体用法
可以参考项目代码，另外还可以参考Web3j官方文档https://web3j.github.io/web3j/ 内容十分详细。
> * 4、其他问题
项目提示JDK Level不是1.8 => Project->Properties->Java Compiler->cancel Enable project specific settings->OK。
