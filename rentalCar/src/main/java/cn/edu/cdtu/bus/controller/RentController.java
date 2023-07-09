package cn.edu.cdtu.bus.controller;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.domain.RentVo;
import cn.edu.cdtu.bus.service.CustomerService;
import cn.edu.cdtu.bus.service.RentService;
import cn.edu.cdtu.sys.domain.User;
import cn.edu.cdtu.sys.utils.RandomUtils;
import cn.edu.cdtu.sys.utils.ResultObj;
import cn.edu.cdtu.sys.utils.SysConstant;
import cn.edu.cdtu.sys.utils.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 *汽车出租管理的控制器
 */
@RestController
@RequestMapping("/rent/")
public class RentController {

    @Resource
    private RentService rentService;
    @Resource
    private CustomerService customerService;

    /**
     *检查身份证号是否存在
     * @param rentVo
     * @return
     */
    @GetMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(RentVo rentVo){
        return customerService.checkCustomerExist(rentVo.getIdentity());
    }

    /**
     * 初始化添加出租单的表单的数据
     * @param rentVo
     * @return
     */
    @GetMapping("initRentForm")
    public RentVo initRentFrom(RentVo rentVo){
        //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());
        //设置操作员
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }

    /**
     * 保存出租单信息
     * @param rentVo
     * @return
     */
    @PostMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo) {
        try {
            this.rentService.addRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询
     */
    @GetMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo){
        return this.rentService.queryAllRent(rentVo);
    }

    /**
     * 修改出租单信息
     * @param rentVo
     * @return
     */
    @PostMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo){
        try {
            //修改
            this.rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除出租单信息
     * @param rentVo
     * @return
     */
    @GetMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo){
        try {
            //删除
            this.rentService.deleteRent(rentVo.getRentid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
