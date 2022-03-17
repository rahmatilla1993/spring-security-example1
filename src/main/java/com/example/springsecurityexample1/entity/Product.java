package com.example.springsecurityexample1.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true)
  private String name;
}
