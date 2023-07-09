package cn.edu.cdtu.sys.service;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.domain.RoleVo;

public interface RoleService {
    /**
     * 查询所有角色
     * @param roleVo
     * @return
     */
    DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加角色
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     * @param roleVo
     */
    void updateRole(RoleVo roleVo);

    /**
     * 根据id删除角色
     * @param roleid
     */
    void deleteRole(Integer roleid);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatchRole(Integer[] ids);

    /**
     * 加载角色管理分配菜单的json
     * @param roleid
     * @return
     */
    DataGridView initRoleMenuTreeJson(Integer roleid);
}
