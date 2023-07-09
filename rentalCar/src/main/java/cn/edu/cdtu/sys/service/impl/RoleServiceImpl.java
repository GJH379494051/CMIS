package cn.edu.cdtu.sys.service.impl;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.domain.Role;
import cn.edu.cdtu.sys.domain.RoleVo;
import cn.edu.cdtu.sys.mapper.RoleMapper;
import cn.edu.cdtu.sys.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Role> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> roles = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(), roles);
    }

    /**
     * 添加角色
     * @param roleVo
     */
    @Override
    public void addRole(RoleVo roleVo) {
        this.roleMapper.insertSelective(roleVo);
    }

    /**
     * 更新角色
     * @param roleVo
     */
    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    /**
     * 根据角色roleid单个删除角色
     * @param roleid
     */
    @Override
    public void deleteRole(Integer roleid) {
        //删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleid);
        //根据角色id删除sys_role_menu里面的数据
        //this.roleMapper.deleteRoleMenuByRid(roleid);
        //根据角色id删除sys_role_user里面的数据 //TODO
        //this.roleMapper.deleteRoleUserByRid(roleid);

    }

    /**
     * 根据前台页面传来的数组批量删除角色
     * @param ids
     */
    @Override
    public void deleteBatchRole(Integer[] ids) {
        for (Integer rid : ids){
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        return null;
    }
}
