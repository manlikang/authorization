package com.qf.oa.service;

import com.qf.oa.entity.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ServiceTest {
    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void selectTest(){
        SysUser user = sysUserService.selectByPrimaryKey(1l);
        System.out.println(user.getUserName());
    }
}
