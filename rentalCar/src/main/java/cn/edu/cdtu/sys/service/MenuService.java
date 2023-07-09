package cn.edu.cdtu.sys.service;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.domain.Menu;
import cn.edu.cdtu.sys.domain.MenuVo;

import java.util.List;

public interface MenuService {
    /**
     * 查询所有的菜单返回List
     * @param menuVo
     * @return
     */
    List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 查询所有菜单
     * @param menuVo
     * @return
     */
    DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     **/
    void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     **/
    void updateMenu(MenuVo menuVo);

    /**
     * 删除菜单
     *
     * @param menuVo
     */
    void deleteMenu(MenuVo menuVo);

    /**
     * 根据pid查询菜单的数量
     **/
    Integer queryMenuByPid(Integer id);
}
