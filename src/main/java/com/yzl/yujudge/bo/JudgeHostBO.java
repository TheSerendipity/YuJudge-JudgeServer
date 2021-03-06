package com.yzl.yujudge.bo;

import com.yzl.yujudge.dto.JudgeHostConditionDTO;

import java.util.Date;

/**
 * 单个判题服务器信息的业务对象
 *
 * @author yuzhanglong
 * @date 2020-08-08 15:10
 */

public class JudgeHostBO {
    private Long id;
    private String name;
    private String baseUrl;
    private Boolean active;
    private Date createTime;
    private JudgeHostConditionDTO condition;
    private Boolean connection;


    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public JudgeHostConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(JudgeHostConditionDTO condition) {
        this.condition = condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JudgeHostBO{" +
                "name='" + name + '\'' +
                ", address='" + baseUrl + '\'' +
                ", active=" + active +
                ", createTime=" + createTime +
                ", condition=" + condition +
                '}';
    }
}