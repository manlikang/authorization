package com.qf.oa.entity.common;

/**
 * @author : FuHan
 * @description : ***
 * @date: 2019/9/27
 */
public class SysMenuOV {

    private long meanId;
    private long parentId;
    private String meanName;
    private boolean open = true;
    private boolean checked;

    public long getMeanId() {
        return meanId;
    }

    public void setMeanId(long meanId) {
        this.meanId = meanId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getMeanName() {
        return meanName;
    }

    public void setMeanName(String meanName) {
        this.meanName = meanName;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "SysMenuOV{" +
                "meanId=" + meanId +
                ", parentId=" + parentId +
                ", meanName='" + meanName + '\'' +
                ", open=" + open +
                ", checked=" + checked +
                '}';
    }
}
