pragma solidity 0.4.11;

contract CrowdFunding {
	// 受益地址
    address beneficiary;
	// 当前金币
    uint amount;
	// 众筹结束事件
    event CrowdEnd(address beneficiary, uint amount);
	// 构造函数
    function CrowdFunding(address addr) {
        beneficiary = addr;
    }
	// 发送金币
    function fund() payable {
        amount += msg.value;
    }
	// 接收金币
    function get() {
        beneficiary.transfer(amount);
        CrowdEnd(beneficiary, amount);
        amount = 0;
    }
}