package cn.edu.cdtu.bus.service;

import cn.edu.cdtu.bus.domain.CheckVo;
import cn.edu.cdtu.bus.domain.DataGridView;

import java.util.Map;

public interface CheckService {
    /**
     * 根据出租单号加载检测单的表单数据
     * @param rentid
     * @return
     */
    Map<String, Object> initCheckFormData(String rentid);

    void addCheck(CheckVo checkVo);

    DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 更新检查单
     * @param checkVo
     */
    void updateCheck(CheckVo checkVo);

    /**
     * 删除检查单
     * @param checkVo
     */
    void deleteCheck(CheckVo checkVo);

    /**
     * 批量删除检查单
     * @param ids
     */
    void deleteBatchCheck(String[] ids);

}
