pragma solidity 0.4.8;

contract TransferContract {
    // ÊÂ¼þ
    event Transfer(address indexed from, address indexed to, uint value);
    // º¯Êý
    function TransferFunc(address from, address to, uint value) {
        Transfer(from, to, value);
    }
}