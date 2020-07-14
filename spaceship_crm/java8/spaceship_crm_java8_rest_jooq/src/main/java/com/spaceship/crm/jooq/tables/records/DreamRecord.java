/*
 * This file is generated by jOOQ.
 */
package com.spaceship.crm.jooq.tables.records;


import com.spaceship.crm.jooq.tables.Dream;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class DreamRecord extends UpdatableRecordImpl<DreamRecord> implements Record3<Long, String, Long> {

    private static final long serialVersionUID = 30731639;

    /**
     * Setter for <code>public.dream.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.dream.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.dream.description</code>.
     */
    public void setDescription(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.dream.description</code>.
     */
    public String getDescription() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.dream.user_id</code>.
     */
    public void setUserId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.dream.user_id</code>.
     */
    public Long getUserId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Dream.DREAM.ID;
    }

    @Override
    public Field<String> field2() {
        return Dream.DREAM.DESCRIPTION;
    }

    @Override
    public Field<Long> field3() {
        return Dream.DREAM.USER_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getDescription();
    }

    @Override
    public Long component3() {
        return getUserId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getDescription();
    }

    @Override
    public Long value3() {
        return getUserId();
    }

    @Override
    public DreamRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public DreamRecord value2(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public DreamRecord value3(Long value) {
        setUserId(value);
        return this;
    }

    @Override
    public DreamRecord values(Long value1, String value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DreamRecord
     */
    public DreamRecord() {
        super(Dream.DREAM);
    }

    /**
     * Create a detached, initialised DreamRecord
     */
    public DreamRecord(Long id, String description, Long userId) {
        super(Dream.DREAM);

        set(0, id);
        set(1, description);
        set(2, userId);
    }
}