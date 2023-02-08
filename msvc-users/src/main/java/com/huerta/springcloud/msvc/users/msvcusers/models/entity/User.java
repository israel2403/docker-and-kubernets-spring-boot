package com.huerta.springcloud.msvc.users.msvcusers.models.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
  private UUID id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  public User() {}

  public User(final String name, final String email, final String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }
}
