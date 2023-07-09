package cn.edu.cdtu.bus.controller;

import cn.edu.cdtu.bus.domain.Customer;
import cn.edu.cdtu.bus.domain.CustomerVo;
import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.service.CustomerService;
import cn.edu.cdtu.sys.utils.ResultObj;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController  //以json形式响应数据
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * 加载客户列表返回DataGridView
     * @param customerVo
     * @return
     */
    @GetMapping("/loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo){
        return this.customerService.queryAllCustomer(customerVo);
    }

    /**
     * 添加一个客户
     * @param customerVo
     * @return
     */
    @PostMapping("/addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
        try{
            customerVo.setCreatetime(new Date());
            this.customerService.addCustomer(customerVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除一个客户
     * @param customerVo
     * @return
     */
    @PostMapping("/deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo){
        try{
            this.customerService.deleteCustomer(customerVo.getIdentity());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改一个客户
     * @param customerVo
     * @return
     */
    @PostMapping("/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo){
        try{
            this.customerService.updateCustomer(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除客户
     * @param customerVo
     * @return
     */
    @RequestMapping("/deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo){
        try{
            this.customerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
