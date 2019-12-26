package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.common.SysMenuOV;
import com.qf.oa.entity.common.SysResult;
import com.qf.oa.mapper.SysMenuMapper;
import com.qf.oa.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/27
 */

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements ISysMenuService {


    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public IBaseDao<SysMenu> getDao() {
        return sysMenuMapper;
    }


    @Override
    public List<SysMenuOV> getMenuOVByRoleId(Long roleId) {
        List<SysMenu> menus = sysMenuMapper.getMenuList();

        List<Long> menuId = sysMenuMapper.getMenuIdByRoleId(roleId);
        System.out.println("menuId: "+menuId);

        List<SysMenuOV> list = new ArrayList<>();
        for(SysMenu sysMenu : menus){
            SysMenuOV sysMenuOV = new SysMenuOV();
            sysMenuOV.setMeanId(sysMenu.getMenuId());
            sysMenuOV.setParentId(sysMenu.getMenuParentId());
            sysMenuOV.setMeanName(sysMenu.getMenuName());

            if(menuId.contains((long)sysMenu.getMenuId())){
                sysMenuOV.setChecked(true);
            }else{
                sysMenuOV.setChecked(false);
            }
            list.add(sysMenuOV);
        }

        return list;
    }

    @Override
    public SysResult authNewMenu(List<Long> ids, long roleId) {

       int j = sysMenuMapper.delAllMenu(roleId);
       int i =  sysMenuMapper.authNewMenu(ids,roleId);
       SysResult result = new SysResult();
       if(i>0){
            result.setResult(true);
       }else{
           result.setResult(false);
       }
       return result;
    }

    @Override
    public List<SysMenu> getMenuByUserId(Long userId) {
        return sysMenuMapper.getMenuByUserId(userId);
    }


}
