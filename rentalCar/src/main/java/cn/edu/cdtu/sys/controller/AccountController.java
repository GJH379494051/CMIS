package cn.edu.cdtu.sys.controller;

import cn.edu.cdtu.sys.service.IAccountService;
import cn.edu.cdtu.sys.utils.ResultObj;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService service;

    @GetMapping("/transfer")
    @Transactional
    public ResultObj accountTransfer(String inName, String outName, double money){
        int status = service.updateTransfer(inName, outName, money);
        //如果执行转账成功
        if(status > 0 ){
            return ResultObj.UPDATE_SUCCESS;
        }else{
            return ResultObj.UPDATE_ERROR;
        }
    }
}
