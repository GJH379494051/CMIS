package cn.edu.cdtu.bus.service;

import cn.edu.cdtu.bus.domain.Customer;
import cn.edu.cdtu.bus.domain.CustomerVo;
import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.utils.ResultObj;

public interface CustomerService {
    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 添加客户
     * @param customerVo
     */
    void addCustomer(CustomerVo customerVo);

    /**
     * 删除客户
     * @param identity
     */
    void deleteCustomer(String identity);

    /**
     * 修改客户
     * @param customerVo
     */
    void updateCustomer(CustomerVo customerVo);

    /**
     * 批量删除客户
     * @param ids
     */
    void deleteBatchCustomer(String[] ids);

    /**
     * 根据身份号查询客户信息
     * @param identity
     * @return
     */
    ResultObj checkCustomerExist(String identity);

    Customer queryCustomerByIdentity(String identity);
}
