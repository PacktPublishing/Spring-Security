package com.packt.springsecurity.backend.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.springsecurity.backend.persistence.dao.IUserJpaDAO;
import com.packt.springsecurity.backend.persistence.model.User;

@Transactional
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserJpaDAO dao;

    public UserService() {
        super();
    }

    // API

    // read

    public User findByName(final String name) {
        return dao.findByName(name);
    }

    public User findById(long id) {
        return dao.findOne(id);
    }

    // write

    public User create(final User entity) {
        return dao.save(entity);
    }

    public void update(final User entity) {
        dao.save(entity);
    }

}
