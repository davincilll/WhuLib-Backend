package com.dfcold.whulibbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author dfcold
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LibConfig> libConfigs;
}
