package cn.edu.cdtu.bus.service;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.domain.Rent;
import cn.edu.cdtu.bus.domain.RentVo;

public interface RentService {
    void addRent(RentVo rentVo);

    DataGridView queryAllRent(RentVo rentVo);

    void updateRent(RentVo rentVo);

    void deleteRent(String rentid);

    Rent queryRentByRentId(String rentid);
}
