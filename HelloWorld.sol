pragma solidity 0.4.8;

contract HelloWorld
{
	// 数据
    uint data;
	// 获取
    function get() constant returns (uint data) {
        return data;
    }
	// 设置
    function set(uint x) {
        data = x;
    }
}