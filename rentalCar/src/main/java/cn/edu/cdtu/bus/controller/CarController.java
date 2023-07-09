package cn.edu.cdtu.bus.controller;

import cn.edu.cdtu.bus.domain.Car;
import cn.edu.cdtu.bus.domain.CarVo;
import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.service.CarService;
import cn.edu.cdtu.sys.utils.AppFileUtils;
import cn.edu.cdtu.sys.utils.ResultObj;
import cn.edu.cdtu.sys.utils.SysConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/car/")
public class CarController {

    @Resource
    private CarService carService;

    /**
     * 加载车辆列表返回DataGridView
     * @param carVo
     * @return
     */
    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(CarVo carVo){
        return this.carService.queryAllCar(carVo);
    }

    /**
     * 添加一个车辆
     * @param carVo
     * @return
     */
    @RequestMapping("addCar")
    public ResultObj addCar(CarVo carVo){
        try{
            carVo.setCreatetime(new Date());
            //如果不是默认图片就去掉图片的_temp的后缀
            if(!carVo.getCarimg().equals(SysConstant.DEFAULT_CAR_IMG)){
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(),             SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除一个车辆
     * @param carVo
     * @return
     */
    @RequestMapping("deleteCar")
    public ResultObj deleteCar(CarVo carVo){
        try {
            this.carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改车辆
     * @param carVo
     * @return
     */
    @RequestMapping("updateCar")
    public ResultObj updateCar(CarVo carVo){
        try{
            String carimg = carVo.getCarimg();
            if (carimg.endsWith(SysConstant.FILE_UPLOAD_TEMP)) {
                String filePath =AppFileUtils.updateFileName(carVo.getCarimg(), SysConstant.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                //把原来的删除
                Car car = this.carService.queryCarByCarNumber(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());

            }
            this.carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除车辆
     * @param carVo
     * @return
     */
    @RequestMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo){
        try{
            this.carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
