package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseDao<SysUser> {

    List<SysUser> getListByCondition(String userName);
    List<SysUser> getUserList();

    List<SysUser> getListByRoleId(long id);

    List<SysUser> getListByRoleIdandName(@Param("roleId") long roleId,@Param("userName") String userName);

    int bathAuth(@Param("ids") List<Long> ids, @Param("roleId") long roleId);

    int delAuth(@Param("userId") long userId, @Param("roleId") long roleId);

    SysUser checkLogin(@Param("userName") String userName, @Param("userPassword") String userPassword);

    SysUser getUserByName(@Param("username") String username);
}