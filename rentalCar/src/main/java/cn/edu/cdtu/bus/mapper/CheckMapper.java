package cn.edu.cdtu.bus.mapper;

import cn.edu.cdtu.bus.domain.Check;
import cn.edu.cdtu.bus.domain.CheckVo;

import java.util.List;

public interface CheckMapper {
    void insertSelective(Check check);

    List<Check> queryAllCheck(CheckVo checkVo);

    void updateByPrimaryKeySelective(CheckVo checkVo);

    void deleteByPrimaryKey(String checkid);
}
