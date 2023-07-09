package cn.edu.cdtu.bus.service.impl;

import cn.edu.cdtu.bus.domain.Customer;
import cn.edu.cdtu.bus.domain.CustomerVo;
import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.bus.mapper.CustomerMapper;
import cn.edu.cdtu.bus.service.CustomerService;
import cn.edu.cdtu.sys.utils.ResultObj;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    /**
     * 查询所有客户信息 分页
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        Page<Customer> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
        List<Customer> customerList = this.customerMapper.queryAllCustomer(customerVo);
        return new DataGridView(page.getTotal(), customerList);
    }

    /**
     * 添加一个客户
     * @param customerVo
     */
    @Override
    public void addCustomer(CustomerVo customerVo) {
        this.customerMapper.insertSelective(customerVo);
    }

    /**
     * 删除一个客户
     * @param identity
     */
    @Override
    public void deleteCustomer(String identity) {
        this.customerMapper.deleteByPrimaryKey(identity);
    }

    /**
     * 修改一个客户
     * @param customerVo
     */
    @Override
    public void updateCustomer(CustomerVo customerVo) {
        this.customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    /**
     * 批量删除客户
     * @param ids
     */
    @Override
    public void deleteBatchCustomer(String[] ids) {
        this.customerMapper.deleteBatch(ids);
    }

    /**
     * 通过身份证号查询客户
     * @param identity
     * @return
     */
    @Override
    public ResultObj checkCustomerExist(String identity) {
        Customer customer = this.customerMapper.selectByPrimaryKey(identity);
        if (!Objects.isNull(customer)) {
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return this.customerMapper.selectByPrimaryKey(identity);
    }


}
