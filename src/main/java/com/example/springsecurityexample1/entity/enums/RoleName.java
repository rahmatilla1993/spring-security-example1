package com.example.springsecurityexample1.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum RoleName {
  USER(Set.of(Permission.PRODUCT_GET)),
  ADMIN(Set.of(Permission.PRODUCT_GET, Permission.PRODUCT_EDIT, Permission.PRODUCT_ADD, Permission.PRODUCT_DELETE));

  private final Set<Permission> permissions;

  public Set<Permission> getPermissions() {
    return permissions;
  }

  RoleName(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public Set<SimpleGrantedAuthority> getAuthorities() {
    return permissions.stream().map(permission -> new SimpleGrantedAuthority(
      permission.getPermission())).collect(Collectors.toSet());
  }
}
