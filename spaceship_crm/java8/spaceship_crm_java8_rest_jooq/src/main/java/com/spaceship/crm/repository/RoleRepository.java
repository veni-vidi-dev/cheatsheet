/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.repository;

import com.spaceship.crm.entity.Role;
import com.spaceship.crm.jooq.Tables;
import com.spaceship.crm.jooq.tables.records.RoleRecord;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    @Autowired
    DSLContext dsl;

//    return dsl
//            .selectFrom(Tables.ACTIVITY)
//            .where(Tables.ACTIVITY.ID.equal(id))
//            .fetchOptional();

    /**
     * select *
     * from user_role ur
     *      left join role r on ur.role_id = r.id
     * where ur.user_id = ?
     */
    public List<RoleRecord>  findAllByUserId(Long userId) {
        return dsl
                .select(Tables.ROLE.ID, Tables.ROLE.NAME)
                .from(Tables.USER_ROLE)
                    .leftJoin(Tables.ROLE).onKey()
                .where(Tables.USER_ROLE.USER_ID.equal(userId))
                .fetch()
                .into(RoleRecord.class);
    }

}
