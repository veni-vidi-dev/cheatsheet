/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.jooq.Tables;
import com.spaceship.crm.jooq.tables.records.ActivityRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActivityRepository  {

    @Autowired
    DSLContext dsl;

    public List<ActivityRecord> findAll() {
        return dsl
                .selectFrom(Tables.ACTIVITY)
                .fetch();
    }

    public Optional<ActivityRecord> findById(Long id) {
        return dsl
                .selectFrom(Tables.ACTIVITY)
                .where(Tables.ACTIVITY.ID.equal(id))
                .fetchOptional();
    }
}
