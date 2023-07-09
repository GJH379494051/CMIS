package cn.edu.cdtu.sys.service.impl;

import cn.edu.cdtu.sys.mapper.AccountMapper;
import cn.edu.cdtu.sys.service.IAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional  //事务控制
public class AccountServiceImpl implements IAccountService {

    @Resource
    private AccountMapper mapper;

    @Override
    public int updateTransfer(String inName, String outName, double money) {
        try{
            //调用转入
            mapper.transferIn(inName,money);
            //调用转出
            mapper.transferOut(outName,money);
            return 1;
        }catch (Exception e){
            System.out.println(e);
            return -1;
        }
    }
}
