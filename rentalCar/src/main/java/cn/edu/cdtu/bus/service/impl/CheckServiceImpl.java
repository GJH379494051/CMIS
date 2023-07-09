package cn.edu.cdtu.bus.service.impl;

import cn.edu.cdtu.bus.domain.*;
import cn.edu.cdtu.bus.mapper.CarMapper;
import cn.edu.cdtu.bus.mapper.CheckMapper;
import cn.edu.cdtu.bus.mapper.CustomerMapper;
import cn.edu.cdtu.bus.mapper.RentMapper;
import cn.edu.cdtu.bus.service.CheckService;
import cn.edu.cdtu.sys.domain.User;
import cn.edu.cdtu.sys.utils.RandomUtils;
import cn.edu.cdtu.sys.utils.SysConstant;
import cn.edu.cdtu.sys.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {

    @Resource
    private RentMapper rentMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CarMapper carMapper;
    @Resource
    private CheckMapper checkMapper;


    @Override
    public Map<String, Object> initCheckFormData(String rentid) {
        //查询出租单
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstant.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user =(User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String, Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);

        return map;
    }

    @Override
    public void addCheck(CheckVo checkVo) {
        checkVo.setCreatetime(new Date());
        this.checkMapper.insertSelective(checkVo);

        //更改出租单的状态为已归还
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        rent.setRentflag(SysConstant.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);

        //更改汽车状态为未出租
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Check> page = PageHelper.startPage(checkVo.getPage(), checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 更新检查单
     * @param checkVo
     */
    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }

    /**
     * 删除检查单
     * @param checkVo
     */
    @Override
    public void deleteCheck(CheckVo checkVo) {
        this.checkMapper.deleteByPrimaryKey(checkVo.getCheckid());
    }

    /**
     * 批量删除检查单
     * @param ids
     */
    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids) {
            this.checkMapper.deleteByPrimaryKey(id);
        }
    }
}
