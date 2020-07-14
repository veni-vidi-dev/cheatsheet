/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(UserActivityId.class)
public class UserActivity {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Activity activity;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;

}
