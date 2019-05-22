package com.packt.springsecurity.backend.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.springsecurity.backend.persistence.dao.IAuthorityJpaDAO;
import com.packt.springsecurity.backend.persistence.model.Authority;

@Transactional
@Service
public class AuthorityService implements IAuthorityService {

    @Autowired
    private IAuthorityJpaDAO dao;

    public AuthorityService() {
        super();
    }

    // API

    // read

    public Authority findByName(final String name) {
        return dao.findByName(name);
    }

    public Authority findById(long id) {
        return dao.findOne(id);
    }

    // write

    public Authority create(final Authority entity) {
        return dao.save(entity);
    }

    public void update(final Authority entity) {
        dao.save(entity);
    }

}
