package com.qf.oa.entity;

import java.util.Date;

public class SysRoleMenu {
    private Long roleMenuRelId;

    private Long roleId;

    private Long menuId;

    private Date createTime;

    private Date updateTime;

    public Long getRoleMenuRelId() {
        return roleMenuRelId;
    }

    public void setRoleMenuRelId(Long roleMenuRelId) {
        this.roleMenuRelId = roleMenuRelId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}