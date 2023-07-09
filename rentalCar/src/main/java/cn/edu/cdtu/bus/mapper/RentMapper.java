package cn.edu.cdtu.bus.mapper;

import cn.edu.cdtu.bus.domain.Rent;
import cn.edu.cdtu.bus.domain.RentVo;

import java.util.List;

public interface RentMapper {
    int insertSelective(Rent record);

    List<Rent> queryAllRent(Rent rent);

    void updateByPrimaryKeySelective(Rent rent);

    Rent selectByPrimaryKey(String rentid);

    void deleteByPrimaryKey(String rentid);
}
