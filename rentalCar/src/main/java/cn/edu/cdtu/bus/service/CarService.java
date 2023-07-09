package cn.edu.cdtu.bus.service;

import cn.edu.cdtu.bus.domain.Car;
import cn.edu.cdtu.bus.domain.CarVo;
import cn.edu.cdtu.bus.domain.DataGridView;

public interface CarService {
    /**
     * 查询所有车辆
     * @param carVo
     * @return
     */
    DataGridView queryAllCar(CarVo carVo);

    /**
     * 添加车辆
     * @param carVo
     */
    void addCar(CarVo carVo);

    /**
     * 根据id删除车辆
     * @param carnumber
     */
    void deleteCar(String carnumber);

    /**
     * 根据车牌号查询
     * @param carnumber
     * @return
     */
    Car queryCarByCarNumber(String carnumber);

    /**
     * 修改车辆
     * @param carVo
     */
    void updateCar(CarVo carVo);

    /**
     * 批量删除车辆
     * @param carnumbers
     */
    void deleteBatchCar(String[] carnumbers);
}
