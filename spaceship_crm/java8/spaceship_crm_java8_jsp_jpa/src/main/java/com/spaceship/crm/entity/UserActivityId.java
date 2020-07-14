/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityId implements Serializable {
    private Long user;
    private Long activity;
}
