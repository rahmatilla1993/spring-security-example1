package com.example.springsecurityexample1.entity;

import com.example.springsecurityexample1.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String firstName;

  @Column(length = 50, nullable = false)
  private String lastName;

  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @ManyToOne(optional = false)
  private Role role;

  @Enumerated(EnumType.STRING)
  private Status status;
}
