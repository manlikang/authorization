package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.entity.SysUser;

import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole> {

    List<SysRole> getList();
}