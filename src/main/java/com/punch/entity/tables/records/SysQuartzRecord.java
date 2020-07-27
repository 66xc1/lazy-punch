/*
 * This file is generated by jOOQ.
 */
package com.punch.entity.tables.records;


import com.punch.entity.tables.SysQuartz;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 定时任务
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysQuartzRecord extends UpdatableRecordImpl<SysQuartzRecord> implements Record6<String, String, String, Integer, String, LocalDateTime> {

    private static final long serialVersionUID = 1587856828;

    /**
     * Setter for <code>punch.sys_quartz.job_key</code>. 任务key
     */
    public void setJobKey(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.job_key</code>. 任务key
     */
    @NotNull
    @Size(max = 64)
    public String getJobKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>punch.sys_quartz.bean_name</code>. bean名称
     */
    public void setBeanName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.bean_name</code>. bean名称
     */
    @Size(max = 200)
    public String getBeanName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>punch.sys_quartz.cron</code>. cron表达式
     */
    public void setCron(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.cron</code>. cron表达式
     */
    @Size(max = 100)
    public String getCron() {
        return (String) get(2);
    }

    /**
     * Setter for <code>punch.sys_quartz.status</code>. 任务状态  1：正常  0：暂停
     */
    public void setStatus(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.status</code>. 任务状态  1：正常  0：暂停
     */
    public Integer getStatus() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>punch.sys_quartz.remark</code>. 备注
     */
    public void setRemark(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.remark</code>. 备注
     */
    @Size(max = 255)
    public String getRemark() {
        return (String) get(4);
    }

    /**
     * Setter for <code>punch.sys_quartz.create_time</code>. 创建时间
     */
    public void setCreateTime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>punch.sys_quartz.create_time</code>. 创建时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, Integer, String, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, Integer, String, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return SysQuartz.SYS_QUARTZ.JOB_KEY;
    }

    @Override
    public Field<String> field2() {
        return SysQuartz.SYS_QUARTZ.BEAN_NAME;
    }

    @Override
    public Field<String> field3() {
        return SysQuartz.SYS_QUARTZ.CRON;
    }

    @Override
    public Field<Integer> field4() {
        return SysQuartz.SYS_QUARTZ.STATUS;
    }

    @Override
    public Field<String> field5() {
        return SysQuartz.SYS_QUARTZ.REMARK;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return SysQuartz.SYS_QUARTZ.CREATE_TIME;
    }

    @Override
    public String component1() {
        return getJobKey();
    }

    @Override
    public String component2() {
        return getBeanName();
    }

    @Override
    public String component3() {
        return getCron();
    }

    @Override
    public Integer component4() {
        return getStatus();
    }

    @Override
    public String component5() {
        return getRemark();
    }

    @Override
    public LocalDateTime component6() {
        return getCreateTime();
    }

    @Override
    public String value1() {
        return getJobKey();
    }

    @Override
    public String value2() {
        return getBeanName();
    }

    @Override
    public String value3() {
        return getCron();
    }

    @Override
    public Integer value4() {
        return getStatus();
    }

    @Override
    public String value5() {
        return getRemark();
    }

    @Override
    public LocalDateTime value6() {
        return getCreateTime();
    }

    @Override
    public SysQuartzRecord value1(String value) {
        setJobKey(value);
        return this;
    }

    @Override
    public SysQuartzRecord value2(String value) {
        setBeanName(value);
        return this;
    }

    @Override
    public SysQuartzRecord value3(String value) {
        setCron(value);
        return this;
    }

    @Override
    public SysQuartzRecord value4(Integer value) {
        setStatus(value);
        return this;
    }

    @Override
    public SysQuartzRecord value5(String value) {
        setRemark(value);
        return this;
    }

    @Override
    public SysQuartzRecord value6(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    @Override
    public SysQuartzRecord values(String value1, String value2, String value3, Integer value4, String value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SysQuartzRecord
     */
    public SysQuartzRecord() {
        super(SysQuartz.SYS_QUARTZ);
    }

    /**
     * Create a detached, initialised SysQuartzRecord
     */
    public SysQuartzRecord(String jobKey, String beanName, String cron, Integer status, String remark, LocalDateTime createTime) {
        super(SysQuartz.SYS_QUARTZ);

        set(0, jobKey);
        set(1, beanName);
        set(2, cron);
        set(3, status);
        set(4, remark);
        set(5, createTime);
    }
}
