package cn.edu.cdtu.sys.service.impl;

import cn.edu.cdtu.bus.domain.DataGridView;
import cn.edu.cdtu.sys.domain.Menu;
import cn.edu.cdtu.sys.domain.MenuVo;
import cn.edu.cdtu.sys.mapper.MenuMapper;
import cn.edu.cdtu.sys.service.MenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return menuMapper.queryAllMenu(menuVo);
    }

    /**
     * 查询所有菜单的实现类
     * @param menuVo
     * @return
     */
    @Override
    public DataGridView queryAllMenu(MenuVo menuVo) {
        Page<Object> page = PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加菜单
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo) {
        this.menuMapper.insertSelective(menuVo);
    }

    /**
     * 修改菜单
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo) {
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 删除菜单
     * @param menuVo
     */
    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单的数据
        this.menuMapper.deleteByPrimaryKey(menuVo.getId());
        //根据id删除sys_role_menu里面的数据 TODO
        //this.menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }

    /**
     * 根据pid查询菜单的数量
     * @param id
     * @return
     */
    @Override
    public Integer queryMenuByPid(Integer id) {
        return this.menuMapper.queryMenuByPid(id);
    }
}
