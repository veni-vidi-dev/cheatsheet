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

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.activity</code>.
     */
    public static final Activity ACTIVITY = Activity.ACTIVITY;

    /**
     * The table <code>public.dream</code>.
     */
    public static final Dream DREAM = Dream.DREAM;

    /**
     * The table <code>public.role</code>.
     */
    public static final Role ROLE = Role.ROLE;

    /**
     * The table <code>public.user_activity</code>.
     */
    public static final UserActivity USER_ACTIVITY = UserActivity.USER_ACTIVITY;

    /**
     * The table <code>public.user_info</code>.
     */
    public static final UserInfo USER_INFO = UserInfo.USER_INFO;

    /**
     * The table <code>public.user_role</code>.
     */
    public static final UserRole USER_ROLE = UserRole.USER_ROLE;

    /**
     * The table <code>public.users</code>.
     */
    public static final Users USERS = Users.USERS;
}
