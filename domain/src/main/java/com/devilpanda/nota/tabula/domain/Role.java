package com.devilpanda.nota.tabula.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

}
