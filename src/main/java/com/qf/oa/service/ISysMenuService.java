package com.qf.oa.service;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.common.SysMenuOV;
import com.qf.oa.entity.common.SysResult;

import java.util.List;

public interface ISysMenuService extends IBaseDao<SysMenu> {

    List<SysMenuOV> getMenuOVByRoleId(Long roleId);

    SysResult authNewMenu(List<Long> ids, long roleId);

    List<SysMenu> getMenuByUserId(Long userId);

}
