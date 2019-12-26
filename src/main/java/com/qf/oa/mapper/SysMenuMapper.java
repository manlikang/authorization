package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu> {


    List<SysMenu> getMenuList();

    List<Long> getMenuIdByRoleId(Long roleId);

    int authNewMenu(@Param("ids") List<Long> ids, @Param("roleId") long roleId);

    int delAllMenu(long roleId);

    List<SysMenu> getMenuByUserId(Long userId);

}