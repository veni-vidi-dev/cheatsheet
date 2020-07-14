/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spaceship.crm.entity.Dream;
import com.spaceship.crm.entity.Role;
import com.spaceship.crm.entity.UserActivity;
import com.spaceship.crm.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBean {

    private Long id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String firstName;
    private String lastName;

    private UserInfo userInfo;

    private List<Role> roles;
    private List<Dream> dreams;
    
}
