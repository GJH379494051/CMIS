package cn.edu.cdtu.bus.mapper;

import cn.edu.cdtu.bus.domain.Car;

import java.util.List;

public interface CarMapper {

    List<Car> queryAllCar(Car car);

    void insertSelective(Car car);

    Car selectByPrimaryKey(String carnumber);

    void deleteByPrimaryKey(String carnumber);

    void updateByPrimaryKeySelective(Car car);
}
