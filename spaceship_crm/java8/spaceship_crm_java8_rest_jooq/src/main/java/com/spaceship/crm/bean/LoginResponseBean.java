/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseBean {
    private UserBean user;
    private String token;
}
