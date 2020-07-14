/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.bean.UserActivityBean;
import com.spaceship.crm.jooq.Tables;
import com.spaceship.crm.jooq.tables.records.UserActivityRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static org.jooq.impl.DSL.row;

//TODO: Framework should be created
@Repository
public class UserActivityRepository {

    @Autowired
    DSLContext dsl;

//    "select u.id, u.first_name, u.last_name, a.name, a.start_date, a.end_date, ua.created_at " +
//            "from user_activity ua " +
//            "   left join users u on ua.user_id = u.id " +
//            "   left join activity a on ua.activity_id = a.id "

    public List<UserActivityBean> findAll() {
        return dsl
                .select(
                    Tables.USERS.ID.as("user_id"),
                    Tables.USERS.FIRST_NAME,
                    Tables.USERS.LAST_NAME,
                    Tables.ACTIVITY.NAME.as("activity_name"),
                    Tables.ACTIVITY.START_DATE.as("start"),
                    Tables.ACTIVITY.END_DATE.as("end"),
                    Tables.USER_ACTIVITY.CREATED_AT.as("added")
                )
                .from(Tables.USER_ACTIVITY)
                    .leftJoin(Tables.USERS).onKey()
                    .leftJoin(Tables.ACTIVITY).onKey()
                .fetch()
                .into(UserActivityBean.class);
    }

    public void insert(Long activityId, Long userId) {
        UserActivityRecord userActivityRecord = dsl.newRecord(Tables.USER_ACTIVITY);
        userActivityRecord.setActivityId(activityId);
        userActivityRecord.setUserId(userId);
        userActivityRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userActivityRecord.store();
    }

    public void delete(Long activityId, Long userId) {
        dsl
                .deleteFrom(Tables.USER_ACTIVITY)
                .where(
                        row(Tables.USER_ACTIVITY.ACTIVITY_ID, Tables.USER_ACTIVITY.USER_ID)
                                .equal(row(activityId, userId)))
                .execute();
    }
}
