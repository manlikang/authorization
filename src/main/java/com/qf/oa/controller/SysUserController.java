package com.qf.oa.controller;

import com.google.gson.Gson;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.common.MyPage;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysMenuService;
import com.qf.oa.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/25
 */

@Controller
@RequestMapping("sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("select")
    public String getById(long id,Model model){
        SysUser user = sysUserService.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        return "ok";
    }

    @RequestMapping("list")
    public String getList(MyPage<SysUser> page,Model model){
        page = sysUserService.getPage(page);
        page.setUrl("sysUser/list");
        model.addAttribute("pageInfo",page);
        return  "/user/user-list";
    }

    @RequestMapping("selectByCondition")
    public String getListByCondition(MyPage<SysUser> page, String userName, Model model){
        page = sysUserService.getPageByCondition(page,userName);
        page.setUrl("sysUser/selectByCondition");
        model.addAttribute("pageInfo",page);
        model.addAttribute("userName",userName);
        Gson gson = new Gson();
        System.out.println(userName);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userName",userName);
        System.out.println("json:"+gson.toJson(paramMap));
        model.addAttribute("params",gson.toJson(paramMap));//生成一个json数据
        return  "/user/user-list";
    }

    @RequestMapping("checkLogin")
    public String checkLogin(Model model,SysUser user,boolean rememberMe){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){

            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
            token.setRememberMe(rememberMe);
            try {
               subject.login(token);

           }catch (AuthenticationException e){
                e.printStackTrace();
                System.out.println("认证失败!!");
                return "login";
            }

        }
        SysUser user1 = sysUserService.getUserByName(user.getUserName());
        List<SysMenu> menus = sysMenuService.getMenuByUserId(user1.getUserId());
        model.addAttribute("menus",menus);
        System.out.println("认证成功");
        return "../index";

    }

}
