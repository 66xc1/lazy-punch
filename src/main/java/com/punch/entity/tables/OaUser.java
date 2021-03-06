/*
 * This file is generated by jOOQ.
 */
package com.punch.entity.tables;


import com.punch.entity.Indexes;
import com.punch.entity.Keys;
import com.punch.entity.Punch;
import com.punch.entity.tables.records.OaUserRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row15;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OaUser extends TableImpl<OaUserRecord> {

    private static final long serialVersionUID = 48449642;

    /**
     * The reference instance of <code>punch.oa_user</code>
     */
    public static final OaUser OA_USER = new OaUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OaUserRecord> getRecordType() {
        return OaUserRecord.class;
    }

    /**
     * The column <code>punch.oa_user.id</code>.
     */
    public final TableField<OaUserRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>punch.oa_user.login_id</code>. oa登录名
     */
    public final TableField<OaUserRecord, String> LOGIN_ID = createField(DSL.name("login_id"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "oa登录名");

    /**
     * The column <code>punch.oa_user.password</code>. oa密码
     */
    public final TableField<OaUserRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "oa密码");

    /**
     * The column <code>punch.oa_user.mobile</code>. 手机号
     */
    public final TableField<OaUserRecord, String> MOBILE = createField(DSL.name("mobile"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "手机号");

    /**
     * The column <code>punch.oa_user.client_model</code>. 手机型号
     */
    public final TableField<OaUserRecord, String> CLIENT_MODEL = createField(DSL.name("client_model"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "手机型号");

    /**
     * The column <code>punch.oa_user.device_name</code>. 设备名称
     */
    public final TableField<OaUserRecord, String> DEVICE_NAME = createField(DSL.name("device_name"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "设备名称");

    /**
     * The column <code>punch.oa_user.device_id</code>. 设备id
     */
    public final TableField<OaUserRecord, String> DEVICE_ID = createField(DSL.name("device_id"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "设备id");

    /**
     * The column <code>punch.oa_user.os_version</code>. 操作系统版本
     */
    public final TableField<OaUserRecord, String> OS_VERSION = createField(DSL.name("os_version"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "操作系统版本");

    /**
     * The column <code>punch.oa_user.oa_user_id</code>. oa用户id
     */
    public final TableField<OaUserRecord, String> OA_USER_ID = createField(DSL.name("oa_user_id"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "oa用户id");

    /**
     * The column <code>punch.oa_user.oa_user_name</code>. 姓名
     */
    public final TableField<OaUserRecord, String> OA_USER_NAME = createField(DSL.name("oa_user_name"), org.jooq.impl.SQLDataType.VARCHAR(16), this, "姓名");

    /**
     * The column <code>punch.oa_user.enable</code>. 是否启用 1启用 0停用
     */
    public final TableField<OaUserRecord, Integer> ENABLE = createField(DSL.name("enable"), org.jooq.impl.SQLDataType.INTEGER, this, "是否启用 1启用 0停用");

    /**
     * The column <code>punch.oa_user.push_id</code>. 通知推送id
     */
    public final TableField<OaUserRecord, String> PUSH_ID = createField(DSL.name("push_id"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "通知推送id");

    /**
     * The column <code>punch.oa_user.create_time</code>. 新增时间
     */
    public final TableField<OaUserRecord, LocalDateTime> CREATE_TIME = createField(DSL.name("create_time"), org.jooq.impl.SQLDataType.LOCALDATETIME, this, "新增时间");

    /**
     * The column <code>punch.oa_user.client_type</code>. 设备类型 2 IOS、3安卓
     */
    public final TableField<OaUserRecord, Integer> CLIENT_TYPE = createField(DSL.name("client_type"), org.jooq.impl.SQLDataType.INTEGER, this, "设备类型 2 IOS、3安卓");

    /**
     * The column <code>punch.oa_user.loginUUID</code>. 登录uuid
     */
    public final TableField<OaUserRecord, String> LOGINUUID = createField(DSL.name("loginUUID"), org.jooq.impl.SQLDataType.VARCHAR(36), this, "登录uuid");

    /**
     * Create a <code>punch.oa_user</code> table reference
     */
    public OaUser() {
        this(DSL.name("oa_user"), null);
    }

    /**
     * Create an aliased <code>punch.oa_user</code> table reference
     */
    public OaUser(String alias) {
        this(DSL.name(alias), OA_USER);
    }

    /**
     * Create an aliased <code>punch.oa_user</code> table reference
     */
    public OaUser(Name alias) {
        this(alias, OA_USER);
    }

    private OaUser(Name alias, Table<OaUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private OaUser(Name alias, Table<OaUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> OaUser(Table<O> child, ForeignKey<O, OaUserRecord> key) {
        super(child, key, OA_USER);
    }

    @Override
    public Schema getSchema() {
        return Punch.PUNCH;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.OA_USER_OA_USER_001);
    }

    @Override
    public UniqueKey<OaUserRecord> getPrimaryKey() {
        return Keys.KEY_OA_USER_PRIMARY;
    }

    @Override
    public List<UniqueKey<OaUserRecord>> getKeys() {
        return Arrays.<UniqueKey<OaUserRecord>>asList(Keys.KEY_OA_USER_PRIMARY);
    }

    @Override
    public OaUser as(String alias) {
        return new OaUser(DSL.name(alias), this);
    }

    @Override
    public OaUser as(Name alias) {
        return new OaUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public OaUser rename(String name) {
        return new OaUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public OaUser rename(Name name) {
        return new OaUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row15 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row15<String, String, String, String, String, String, String, String, String, String, Integer, String, LocalDateTime, Integer, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }
}
