/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.entity.User;
import com.spaceship.crm.jooq.Tables;
import com.spaceship.crm.jooq.tables.UserInfo;
import com.spaceship.crm.jooq.tables.Users;
import com.spaceship.crm.jooq.tables.records.ActivityRecord;
import com.spaceship.crm.jooq.tables.records.UserInfoRecord;
import com.spaceship.crm.jooq.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.lambda.tuple.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @Autowired
    DSLContext dsl;

    public List<UsersRecord> findAll() {
        return dsl
                .selectFrom(Tables.USERS)
                .fetch();
    }

    public Optional<UsersRecord> findById(Long id) {
        return dsl
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.ID.eq(id))
                .fetchOptional();
    }

    public Optional<UsersRecord> findByUsername(String username) {
        return dsl
                .selectFrom(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOptional();
    }

}
