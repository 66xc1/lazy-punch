/*
 * This file is generated by jOOQ.
 */
package com.punch.entity.tables;


import com.punch.entity.Keys;
import com.punch.entity.Punch;
import com.punch.entity.tables.records.SysQuartzRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * 定时任务
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SysQuartz extends TableImpl<SysQuartzRecord> {

    private static final long serialVersionUID = -229217748;

    /**
     * The reference instance of <code>punch.sys_quartz</code>
     */
    public static final SysQuartz SYS_QUARTZ = new SysQuartz();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SysQuartzRecord> getRecordType() {
        return SysQuartzRecord.class;
    }

    /**
     * The column <code>punch.sys_quartz.job_key</code>. 任务key
     */
    public final TableField<SysQuartzRecord, String> JOB_KEY = createField(DSL.name("job_key"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "任务key");

    /**
     * The column <code>punch.sys_quartz.bean_name</code>. bean名称
     */
    public final TableField<SysQuartzRecord, String> BEAN_NAME = createField(DSL.name("bean_name"), org.jooq.impl.SQLDataType.VARCHAR(200), this, "bean名称");

    /**
     * The column <code>punch.sys_quartz.cron</code>. cron表达式
     */
    public final TableField<SysQuartzRecord, String> CRON = createField(DSL.name("cron"), org.jooq.impl.SQLDataType.VARCHAR(100), this, "cron表达式");

    /**
     * The column <code>punch.sys_quartz.status</code>. 任务状态  1：正常  0：暂停
     */
    public final TableField<SysQuartzRecord, Integer> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.INTEGER, this, "任务状态  1：正常  0：暂停");

    /**
     * The column <code>punch.sys_quartz.remark</code>. 备注
     */
    public final TableField<SysQuartzRecord, String> REMARK = createField(DSL.name("remark"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "备注");

    /**
     * The column <code>punch.sys_quartz.create_time</code>. 创建时间
     */
    public final TableField<SysQuartzRecord, LocalDateTime> CREATE_TIME = createField(DSL.name("create_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "创建时间");

    /**
     * Create a <code>punch.sys_quartz</code> table reference
     */
    public SysQuartz() {
        this(DSL.name("sys_quartz"), null);
    }

    /**
     * Create an aliased <code>punch.sys_quartz</code> table reference
     */
    public SysQuartz(String alias) {
        this(DSL.name(alias), SYS_QUARTZ);
    }

    /**
     * Create an aliased <code>punch.sys_quartz</code> table reference
     */
    public SysQuartz(Name alias) {
        this(alias, SYS_QUARTZ);
    }

    private SysQuartz(Name alias, Table<SysQuartzRecord> aliased) {
        this(alias, aliased, null);
    }

    private SysQuartz(Name alias, Table<SysQuartzRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("定时任务"), TableOptions.table());
    }

    public <O extends Record> SysQuartz(Table<O> child, ForeignKey<O, SysQuartzRecord> key) {
        super(child, key, SYS_QUARTZ);
    }

    @Override
    public Schema getSchema() {
        return Punch.PUNCH;
    }

    @Override
    public UniqueKey<SysQuartzRecord> getPrimaryKey() {
        return Keys.KEY_SYS_QUARTZ_PRIMARY;
    }

    @Override
    public List<UniqueKey<SysQuartzRecord>> getKeys() {
        return Arrays.<UniqueKey<SysQuartzRecord>>asList(Keys.KEY_SYS_QUARTZ_PRIMARY);
    }

    @Override
    public SysQuartz as(String alias) {
        return new SysQuartz(DSL.name(alias), this);
    }

    @Override
    public SysQuartz as(Name alias) {
        return new SysQuartz(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SysQuartz rename(String name) {
        return new SysQuartz(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SysQuartz rename(Name name) {
        return new SysQuartz(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, Integer, String, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }
}