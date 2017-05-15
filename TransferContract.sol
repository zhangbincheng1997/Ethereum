pragma solidity 0.4.8;

contract TransferContract {
	// 事件
    event Transfer(address indexed from, address indexed to, uint value);
	// 函数
    function TransferFunc(address from, address to, uint value) {
        Transfer(from, to, value);
    }
}