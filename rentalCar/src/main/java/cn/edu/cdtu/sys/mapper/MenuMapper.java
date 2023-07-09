package cn.edu.cdtu.sys.mapper;


import cn.edu.cdtu.sys.domain.Menu;
import cn.edu.cdtu.sys.domain.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询所有菜单
     */
    List<Menu> queryAllMenu(Menu menu);

    void insertSelective(Menu menu);

    void updateByPrimaryKeySelective(Menu menu);

    int deleteByPrimaryKey(Integer id);

    /**
     * 根据pid查询菜单数量
     * @param id
     * @return
     */
    Integer queryMenuByPid(Integer id);
}

