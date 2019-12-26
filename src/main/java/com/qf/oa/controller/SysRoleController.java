package com.qf.oa.controller;

import com.google.gson.Gson;
import com.qf.oa.entity.common.MyPage;
import com.qf.oa.entity.common.SysResult;
import com.qf.oa.entity.SysRole;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysRoleService;
import com.qf.oa.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/26
 */
@Controller
@RequestMapping("sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("load")
    public String load(Model model) {
        List<SysRole> list = sysRoleService.getList();
        model.addAttribute("list", list);
        return "/auth/authorization";
    }

    @RequestMapping("getUserByRoleId")
    public String getUserByRoleId(Model model, long roleId, MyPage<SysUser> myPage) {
        myPage = sysUserService.getUserByRoleId(myPage, roleId);
        myPage.setUrl("sysRole/getUserByRoleId");
        model.addAttribute("pageInfo", myPage);
        model.addAttribute("roleId", roleId);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        model.addAttribute("params", gson.toJson(map));
        return "/auth/auth_user";
    }


    @RequiresPermissions("user:add")
    @RequestMapping("getNoAuthUser")
    public String getNoAuthUser(Model model, long roleId, String userName, MyPage<SysUser> myPage) {
        myPage = sysUserService.getNoAuthUser(myPage, roleId, userName);
        myPage.setUrl("sysRole/getNoAuthUser");
        model.addAttribute("pageInfo", myPage);
        System.out.println("进入"+roleId);
        model.addAttribute("roleId", roleId);
        model.addAttribute("userName", userName);
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("userName", userName);
        model.addAttribute("params", gson.toJson(map));
        return "auth/noAuth";
    }

    @RequestMapping("bathAuth")
    @ResponseBody
    public SysResult bathAuth(@RequestParam("ids") List<Long> ids, long roleId) {
        return sysUserService.bathAuth(ids, roleId);

    }

    @RequestMapping("delAuth")
    @ResponseBody
    public SysResult delAuth(long userId, long roleId) {
        return sysUserService.delAuth(userId, roleId);
    }
}
