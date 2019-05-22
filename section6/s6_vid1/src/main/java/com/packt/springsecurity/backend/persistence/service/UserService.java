package com.packt.springsecurity.backend.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        return dao.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.username == #username")
    public User findByName(final String username) {
        return dao.findByName(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or principal.id == #id")
    public User findById(final long id) {
        return dao.findOne(id);
    }

    // write

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User create(final User entity) {
        return dao.save(entity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(final User entity) {
        dao.save(entity);
    }

}
