package com.packt.springsecurity.backend.persistence.service;

import com.packt.springsecurity.backend.persistence.model.User;

public interface IUserService {

    // read

    public User findByName(final String name);

    public User findById(final long id);

    // write

    public User create(final User entity);

    public void update(final User entity);

}
