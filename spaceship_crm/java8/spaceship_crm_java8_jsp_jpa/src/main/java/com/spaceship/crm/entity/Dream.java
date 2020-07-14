/**
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
package com.spaceship.crm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dream {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
