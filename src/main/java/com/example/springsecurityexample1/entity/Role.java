package com.example.springsecurityexample1.entity;

import com.example.springsecurityexample1.entity.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  private RoleName roleName;

  public Role(RoleName roleName) {
    this.roleName = roleName;
  }

}
