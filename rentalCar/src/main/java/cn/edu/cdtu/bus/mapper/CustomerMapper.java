package cn.edu.cdtu.bus.mapper;

import cn.edu.cdtu.bus.domain.Customer;
import cn.edu.cdtu.bus.domain.CustomerVo;

import java.util.List;

public interface CustomerMapper {
    /**
     * 查询
     * @param customer
     * @return
     */
    List<Customer> queryAllCustomer(Customer customer);

    /**
     * 添加客户
     * @param customer
     */
    void insertSelective(Customer customer);

    /**
     * 删除客户
     * @param identity
     */
    void deleteByPrimaryKey(String identity);

    /**
     * 修改客户
     * @param customerVo
     */
    void updateByPrimaryKeySelective(CustomerVo customerVo);

    /**
     * 批量删除客户
     * @param ids
     */
    void deleteBatch(String[] ids);

    Customer selectByPrimaryKey(String identity);
}
