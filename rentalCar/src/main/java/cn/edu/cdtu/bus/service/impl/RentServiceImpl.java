package cn.edu.cdtu.bus.service.impl;

import cn.edu.cdtu.bus.domain.Car;
import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.domain.Rent;
import cn.edu.cdtu.bus.domain.RentVo;
import cn.edu.cdtu.bus.mapper.CarMapper;
import cn.edu.cdtu.bus.mapper.RentMapper;
import cn.edu.cdtu.bus.service.RentService;
import cn.edu.cdtu.sys.utils.SysConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Resource
    private RentMapper rentMapper;
    @Resource
    private CarMapper carMapper;

    @Override
    public void addRent(RentVo rentVo) {
        //设置创建时间
        rentVo.setCreatetime(new Date());
        //设置归还状态
        rentVo.setRentflag(SysConstant.RENT_BACK_FALSE);
        //保存
        this.rentMapper.insertSelective(rentVo);

        //更改车辆出租的状态
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        //设置状态为已出租
        car.setIsrenting(SysConstant.RENT_BACK_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Rent> page = PageHelper.startPage(rentVo.getPage(),rentVo.getLimit());
        List<Rent> data = this.rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        this.rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public void deleteRent(String rentid) {
        //更改汽车状态，将已出租的状态转换成未出租的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        //转换成未出租的状态
        car.setIsrenting(SysConstant.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);

        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }
}
