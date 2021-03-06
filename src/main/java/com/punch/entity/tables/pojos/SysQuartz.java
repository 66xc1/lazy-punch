/*
 * This file is generated by jOOQ.
 */
package com.punch.entity.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * 定时任务
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysQuartz implements Serializable {

    private static final long serialVersionUID = -1651896004;

    private String        jobKey;
    private String        beanName;
    private String        cron;
    private Integer       status;
    private String        remark;
    private LocalDateTime createTime;

    public SysQuartz() {}

    public SysQuartz(SysQuartz value) {
        this.jobKey = value.jobKey;
        this.beanName = value.beanName;
        this.cron = value.cron;
        this.status = value.status;
        this.remark = value.remark;
        this.createTime = value.createTime;
    }

    public SysQuartz(
        String        jobKey,
        String        beanName,
        String        cron,
        Integer       status,
        String        remark,
        LocalDateTime createTime
    ) {
        this.jobKey = jobKey;
        this.beanName = beanName;
        this.cron = cron;
        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
    }

    @NotNull
    @Size(max = 64)
    public String getJobKey() {
        return this.jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    @Size(max = 200)
    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Size(max = 100)
    public String getCron() {
        return this.cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Size(max = 255)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SysQuartz (");

        sb.append(jobKey);
        sb.append(", ").append(beanName);
        sb.append(", ").append(cron);
        sb.append(", ").append(status);
        sb.append(", ").append(remark);
        sb.append(", ").append(createTime);

        sb.append(")");
        return sb.toString();
    }
}
