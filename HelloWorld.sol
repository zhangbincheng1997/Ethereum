pragma solidity ^0.4.0;

contract HelloWorld
{
    // 数据
    uint data;
    // 获取
    function get() public constant returns (uint) {
        return data;
    }
    // 设置
    function set(uint x) public {
        data = x;
    }
}
