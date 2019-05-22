package com.packt.springsecurity.backend.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packt.springsecurity.backend.model.Possession;
import com.packt.springsecurity.backend.persistence.dao.IPossessionJpaDAO;

@Transactional
@Service
public class PossessionService implements IPossessionService {

    @Autowired
    private IPossessionJpaDAO dao;

    public PossessionService() {
        super();
    }

    // API

    // read

    public Possession findByName(final String name) {
        return dao.findByName(name);
    }

    @PostAuthorize("hasPermission(returnObject, 'read')")
    public Possession findById(final long id) {
        return dao.findOne(id);
    }

    // @PostFilter("hasPermission(filterObject, 'read')")
    public List<Possession> findAll() {
        return dao.findAll();
    }

    // write

    public Possession create(final Possession entity) {
        return dao.save(entity);
    }

    public void update(final Possession entity) {
        dao.save(entity);
    }

}
