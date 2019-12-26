package com.qf.oa.service;

import com.qf.oa.dao.IBaseDao;
import org.springframework.stereotype.Service;
import com.qf.oa.entity.SysRole;

import java.util.List;

@Service
public interface ISysRoleService extends IBaseDao<SysRole> {


    List<SysRole> getList();
}
