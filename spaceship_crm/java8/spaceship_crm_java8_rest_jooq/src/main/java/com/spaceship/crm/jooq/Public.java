/*
 * This file is generated by jOOQ.
 */
package com.spaceship.crm.jooq;


import com.spaceship.crm.jooq.tables.Activity;
import com.spaceship.crm.jooq.tables.Dream;
import com.spaceship.crm.jooq.tables.Role;
import com.spaceship.crm.jooq.tables.UserActivity;
import com.spaceship.crm.jooq.tables.UserInfo;
import com.spaceship.crm.jooq.tables.UserRole;
import com.spaceship.crm.jooq.tables.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 160539196;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.activity</code>.
     */
    public final Activity ACTIVITY = com.spaceship.crm.jooq.tables.Activity.ACTIVITY;

    /**
     * The table <code>public.dream</code>.
     */
    public final Dream DREAM = com.spaceship.crm.jooq.tables.Dream.DREAM;

    /**
     * The table <code>public.role</code>.
     */
    public final Role ROLE = com.spaceship.crm.jooq.tables.Role.ROLE;

    /**
     * The table <code>public.user_activity</code>.
     */
    public final UserActivity USER_ACTIVITY = com.spaceship.crm.jooq.tables.UserActivity.USER_ACTIVITY;

    /**
     * The table <code>public.user_info</code>.
     */
    public final UserInfo USER_INFO = com.spaceship.crm.jooq.tables.UserInfo.USER_INFO;

    /**
     * The table <code>public.user_role</code>.
     */
    public final UserRole USER_ROLE = com.spaceship.crm.jooq.tables.UserRole.USER_ROLE;

    /**
     * The table <code>public.users</code>.
     */
    public final Users USERS = com.spaceship.crm.jooq.tables.Users.USERS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Activity.ACTIVITY,
            Dream.DREAM,
            Role.ROLE,
            UserActivity.USER_ACTIVITY,
            UserInfo.USER_INFO,
            UserRole.USER_ROLE,
            Users.USERS);
    }
}
