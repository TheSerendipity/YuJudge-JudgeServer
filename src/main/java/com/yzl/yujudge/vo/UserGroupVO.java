package com.yzl.yujudge.vo;

/**
 * 用户组信息视图层对象
 *
 * @author yuzhanglong
 * @date 2020-8-22 11:59:29
 */
public class UserGroupVO {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserGroupVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
