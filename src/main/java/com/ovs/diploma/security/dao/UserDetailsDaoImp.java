package com.ovs.diploma.security.dao;

import com.ovs.diploma.security.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao {

  @Autowired
  private SessionFactory sessionFactory;

  @SuppressWarnings("unchecked")
  public User findUserByUsername(String username) {
    System.out.println(username);
    List<User> users = new ArrayList<User>();
    Query query = sessionFactory.getCurrentSession().createQuery("from User where username= ?").setParameter(0, username);
    users = query.list();

    if (users.size() > 0) {
      return users.get(0);
    } else {
      return null;
    }

  }
}
