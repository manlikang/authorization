package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.mapper.SysRoleMapper;
import com.qf.oa.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/26
 */

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {


    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public IBaseDao<SysRole> getDao() {
        return sysRoleMapper;
    }



    @Override
    public List<SysRole> getList() {
        return sysRoleMapper.getList();
    }
}
