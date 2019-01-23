package com.ovs.diploma.security.dao;

import com.ovs.diploma.security.model.User;

public interface UserDetailsDao {
  User findUserByUsername(String username);
}
