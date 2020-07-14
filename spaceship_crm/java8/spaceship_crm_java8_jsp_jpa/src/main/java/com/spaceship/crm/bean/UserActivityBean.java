/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityBean {
    private Long userId;
    private String firstName;
    private String lastName;

    private String activityName;

    private Date start;
    private Date end;
    private Date added;
}
