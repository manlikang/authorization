package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.common.MyPage;
import com.qf.oa.entity.common.SysResult;
import com.qf.oa.entity.SysUser;
import com.qf.oa.mapper.SysUserMapper;
import com.qf.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/25
 */

@Service
public class SysUserServiceImpl extends  BaseServiceImpl<SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public IBaseDao<SysUser> getDao() {
        return sysUserMapper;
    }

    @Override
    public MyPage<SysUser> getPage(MyPage<SysUser> page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.getUserList();
        return  new MyPage<>(list);
    }

    @Override
    public MyPage<SysUser> getPageByCondition(MyPage<SysUser> page, String userName) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.getListByCondition(userName);
        return  new MyPage<>(list);
    }

    @Override
    public MyPage<SysUser> getUserByRoleId(MyPage<SysUser> page, long id) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.getListByRoleId(id);
        return  new MyPage<>(list);
    }

    @Override
    public MyPage<SysUser> getNoAuthUser(MyPage<SysUser> myPage, long roleId, String userName) {
        PageHelper.startPage(myPage.getCurrentPage(),myPage.getPageSize());
        List<SysUser> list = sysUserMapper.getListByRoleIdandName(roleId,userName);
        return  new MyPage<>(list);
    }

    @Override
    public SysResult bathAuth(List<Long> ids, long roleId) {
        int count = sysUserMapper.bathAuth(ids,roleId);
        SysResult result = new SysResult();
        if(count>0){
            result.setResult(true);
        }else{
            result.setResult(false);
        }
        return result;
    }

    @Override
    public SysResult delAuth(long userId, long roleId) {
        int count = sysUserMapper.delAuth(userId,roleId);
        SysResult result = new SysResult();
        if(count>0){
            result.setResult(true);
        }else{
            result.setResult(false);
        }
        return result;
    }

    @Override
    public SysUser checkLogin(String userName, String userPassword) {
        return sysUserMapper.checkLogin(userName, userPassword);
    }

    @Override
    public SysUser getUserByName(String username) {
        return  sysUserMapper.getUserByName(username);
    }


}
