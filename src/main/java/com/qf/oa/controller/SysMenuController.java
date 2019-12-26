package com.qf.oa.controller;

import com.google.gson.Gson;
import com.qf.oa.entity.common.SysMenuOV;
import com.qf.oa.entity.common.SysResult;
import com.qf.oa.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/27
 */

@Controller
@RequestMapping("sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("getMenuByRoleId")
    public String getMenuByRoleId(Model model, Long roleId) {
        System.out.println("123");
        List<SysMenuOV> sysMenuOVS = sysMenuService.getMenuOVByRoleId(roleId);
        for (SysMenuOV sysMenuOV : sysMenuOVS) {
            System.out.println(sysMenuOV);
        }
        model.addAttribute("menu", new Gson().toJson(sysMenuOVS));
        model.addAttribute("roleId",roleId);
        return "menu/menu-list";
    }

    @RequestMapping("AuthNewMenu")
    @ResponseBody
    public SysResult AuthNewMenu(@RequestParam("ids") List<Long> ids, long roleId){
        System.out.println("ids::"+ids);
       return sysMenuService.authNewMenu(ids,roleId);
    }
}
