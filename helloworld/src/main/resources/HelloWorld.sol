pragma solidity ^0.5.0;

contract HelloWorld {

    uint data;

    function get() public view returns (uint) {
        return data;
    }

    function set(uint _data) public {
        data = _data;
    }
}