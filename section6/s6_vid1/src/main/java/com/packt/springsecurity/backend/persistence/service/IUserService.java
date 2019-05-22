package com.packt.springsecurity.backend.persistence.service;

import java.util.List;

import com.packt.springsecurity.backend.persistence.model.User;

public interface IUserService {

    // read

    List<User> findAll();

    public User findByName(final String name);

    public User findById(final long id);

    // write

    public User create(final User entity);

    public void update(final User entity);

}
