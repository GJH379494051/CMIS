package cn.edu.cdtu.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
Second commit
 third commit
*/
@Controller
@RequestMapping("/bus/")
public class BusController {
    /**
     * 跳转到客户管理
     * 提交
     * @return
     */
    @GetMapping("toCustomerManager")
    public String toCustomerManager(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到车辆管理
     * @return
     */
    @GetMapping("toCarManager")
    public String toCarManager(){
        return "business/car/carManager";
    }

    /**
     * 跳转到车辆出租
     * @return
     */
    @GetMapping("toRentCarManager")
    public String toRentCarManager(){
        return "business/rent/rentCarManager";
    }

    /**
     * 跳转到出租单管理的页面
     * @return
     */
    @GetMapping("toRentManager")
    public String toRentManager(){
        return "business/rent/rentManager";
    }

    /**
     * 跳转到汽车入库管理的页面
     * @return
     */
    @RequestMapping("toCheckCarManager")
    public String toCheckCarManager(){
        return "business/check/checkCarManager";
    }

    /**
     * 跳转到检查单管理的页面
     * @return
     */
    @RequestMapping("toCheckManager")
    public String toCheckManager(){
        return "business/check/checkManager";
    }
}
