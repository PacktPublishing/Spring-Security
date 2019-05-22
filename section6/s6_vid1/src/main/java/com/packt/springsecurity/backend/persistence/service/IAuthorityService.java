package com.packt.springsecurity.backend.persistence.service;

import com.packt.springsecurity.backend.persistence.model.Authority;

public interface IAuthorityService {

    // read

    public Authority findByName(final String name);

    public Authority findById(final long id);

    // write

    public Authority create(final Authority entity);

    public void update(final Authority entity);

}
