pragma solidity 0.4.8;

contract HelloWorld
{
    uint data;
    
    function get() constant returns (uint data) {
        return data;
    }
    
    function set(uint x) {
        data = x;
    }
}