# 以太坊开发

## Geth以太坊客户端
1、下载地址https://geth.ethereum.org/downloads/ 貌似我打不开的样子？  
2、打开CMD，切换到Geth文件夹下：  
```
cd path...
```
3、如果是第一次启动Geth，需要初始化创世块：  
```
geth --datadir "%cd%\chain" init hdgenesis.json

init
# 指定创世块文件的位置，并创建初始块
datadir
# 设置当前区块链网络数据存放的位置
```
4、启动Geth，PS：可将命令放在startup.bat脚本，只要双击脚本即可启动。  
```
geth --identity "HDetherum" --rpc --rpccorsdomain "*" --datadir "%cd%\chain" --port "30303" --rpcapi "db,eth,net,web3" --networkid 95520 console

identity
# 区块链的标示，随便填写，用于标示目前网络的名字
rpc
# 启动rpc通信，可以进行智能合约的部署和调试
port
# 网络监听端口
rpcapi
# 设置允许连接的rpc的客户端，一般为db,eth,net,web3
networkid
# 设置当前区块链的网络ID，用于区分不同的网络，是一个数字
console
# 启动命令行模式，可以在Geth中执行命令
```

## Ethereum Wallet钱包客户端
1、下载地址https://github.com/ethereum/mist/releases/ 找到对应版本下载。  
2、在Wallets处创建账号，默认首个创建为主账号，挖矿所得的以太币都将进入这个账号，其余为普通账号，可以用来测试。  
3、在Contracts处发布智能合约，输入HelloWorld.sol和TransferContract.sol代码，点击部署。  
4、这个时候看不到部署的智能合约，需要切换到Geth进行挖矿，挖到一定数量的矿之后，智能合约才能确认并且显示出来：  
```
miner.start(1)
# 开启一个线程挖矿，多线程会很卡
miner.stop()
# 关闭挖矿命令
```

## Web3j 轻量级的以太坊开发库for Java
1、创建Maven工程，不懂自行百度。   
2、打开pom.xml，添加Web3j依赖：  
```
<dependencies>
	<dependency>
		<groupId>org.web3j</groupId>
		<artifactId>core</artifactId>
		<version>2.1.0</version>
	</dependency>
</dependencies>
```
3、右键工程、点击Maven、选择Update Project。  
4、具体用法可以参考项目代码，另外还可以参考Web3j官方文档https://web3j.github.io/web3j/ 内容十分详细。  

附：  
存在问题：项目可能提示JDK Level不是1.8  
解决办法：右键工程、点击Properties，选择Java Compiler、取消Enable project specific settings、点击OK。
