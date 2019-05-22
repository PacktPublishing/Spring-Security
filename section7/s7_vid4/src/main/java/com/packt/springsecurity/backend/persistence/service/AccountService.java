package com.packt.springsecurity.backend.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.springsecurity.backend.model.Account;
import com.packt.springsecurity.backend.persistence.dao.IAccountJpaDAO;

@Transactional
@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountJpaDAO dao;

    public AccountService() {
        super();
    }

    // API

    // read

    public Account findByName(final String username) {
        return dao.findByUsername(username);
    }

    public Account findById(long id) {
        return dao.findOne(id);
    }

    // write

    public Account create(final Account entity) {
        return dao.save(entity);
    }

    public void update(final Account entity) {
        dao.save(entity);
    }

}
