package cn.edu.cdtu.sys.mapper;

import cn.edu.cdtu.sys.domain.Role;
import cn.edu.cdtu.sys.domain.RoleVo;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询角色
     * @param role
     * @return
     */
    List<Role> queryAllRole(Role role);

    void insertSelective(Role role);

    void updateByPrimaryKeySelective(Role role);

    void deleteByPrimaryKey(Integer roleid);

    /**
     * 根据角色id删除sys_role_menu里面的数据
     * @param roleid
     */
    // void deleteRoleMenuByRid(Integer roleid);

    /**
     * 根据角色id删除sys_role_user里面的数据
     * @param roleid
     */
    // void deleteRoleUserByRid(Integer roleid);
}
