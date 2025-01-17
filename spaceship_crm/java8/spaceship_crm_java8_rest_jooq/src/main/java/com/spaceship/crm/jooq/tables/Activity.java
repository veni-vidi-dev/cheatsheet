/*
 * This file is generated by jOOQ.
 */
package com.spaceship.crm.jooq.tables;


import com.spaceship.crm.jooq.Indexes;
import com.spaceship.crm.jooq.Keys;
import com.spaceship.crm.jooq.Public;
import com.spaceship.crm.jooq.tables.records.ActivityRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Activity extends TableImpl<ActivityRecord> {

    private static final long serialVersionUID = -708175830;

    /**
     * The reference instance of <code>public.activity</code>
     */
    public static final Activity ACTIVITY = new Activity();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ActivityRecord> getRecordType() {
        return ActivityRecord.class;
    }

    /**
     * The column <code>public.activity.id</code>.
     */
    public final TableField<ActivityRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.activity.end_date</code>.
     */
    public final TableField<ActivityRecord, Timestamp> END_DATE = createField(DSL.name("end_date"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.activity.name</code>.
     */
    public final TableField<ActivityRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.activity.start_date</code>.
     */
    public final TableField<ActivityRecord, Timestamp> START_DATE = createField(DSL.name("start_date"), org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>public.activity</code> table reference
     */
    public Activity() {
        this(DSL.name("activity"), null);
    }

    /**
     * Create an aliased <code>public.activity</code> table reference
     */
    public Activity(String alias) {
        this(DSL.name(alias), ACTIVITY);
    }

    /**
     * Create an aliased <code>public.activity</code> table reference
     */
    public Activity(Name alias) {
        this(alias, ACTIVITY);
    }

    private Activity(Name alias, Table<ActivityRecord> aliased) {
        this(alias, aliased, null);
    }

    private Activity(Name alias, Table<ActivityRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Activity(Table<O> child, ForeignKey<O, ActivityRecord> key) {
        super(child, key, ACTIVITY);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ACTIVITY_PKEY, Indexes.IDXCHN68HOQYOUNT8VQ94YSX4RL5);
    }

    @Override
    public UniqueKey<ActivityRecord> getPrimaryKey() {
        return Keys.ACTIVITY_PKEY;
    }

    @Override
    public List<UniqueKey<ActivityRecord>> getKeys() {
        return Arrays.<UniqueKey<ActivityRecord>>asList(Keys.ACTIVITY_PKEY);
    }

    @Override
    public Activity as(String alias) {
        return new Activity(DSL.name(alias), this);
    }

    @Override
    public Activity as(Name alias) {
        return new Activity(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Activity rename(String name) {
        return new Activity(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Activity rename(Name name) {
        return new Activity(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Timestamp, String, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
