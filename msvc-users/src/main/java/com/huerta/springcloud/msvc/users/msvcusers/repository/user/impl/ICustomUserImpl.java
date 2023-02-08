package com.huerta.springcloud.msvc.users.msvcusers.repository.user.impl;

import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;
import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User_;
import com.huerta.springcloud.msvc.users.msvcusers.repository.user.ICustomUser;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class ICustomUserImpl implements ICustomUser {

  @PersistenceContext
  private EntityManager em;

  @Override
  public User update(UUID id, User user) {
    CriteriaBuilder cb = this.em.getCriteriaBuilder();

    // create update
    CriteriaUpdate<User> update = cb.createCriteriaUpdate(User.class);

    // set the root class
    final Root<User> root = update.from(User.class);

    // set update and where clasue
    update.set(User_.NAME, user.getName());
    update.set(User_.PASSWORD, user.getPassword());
    update.where(cb.equal(root.get(User_.ID), user.getId()));
    this.em.createQuery(update).executeUpdate();
    return user;
  }
}
