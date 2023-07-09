package cn.edu.cdtu.sys.service;

public interface IAccountService {

    //转账
    int updateTransfer(String inName, String outName , double money);
}
