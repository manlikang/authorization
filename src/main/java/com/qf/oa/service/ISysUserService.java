package com.qf.oa.service;

import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.common.MyPage;
import com.qf.oa.entity.common.SysResult;
import com.qf.oa.entity.SysUser;

import java.util.List;

public interface ISysUserService extends  IBaseService<SysUser> {
    MyPage<SysUser> getPage(MyPage<SysUser> page);

    MyPage<SysUser> getPageByCondition(MyPage<SysUser> page, String userName);
    MyPage<SysUser> getUserByRoleId(MyPage<SysUser> page,long id);

    MyPage<SysUser> getNoAuthUser(MyPage<SysUser> myPage, long roleId, String userName);

    SysResult bathAuth(List<Long> ids, long roleId);

    SysResult delAuth(long userId, long roleId);

    SysUser checkLogin(String userName, String userPassword);

    SysUser getUserByName(String username);
}
