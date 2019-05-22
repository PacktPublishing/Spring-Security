package com.packt.springsecurity.backend.persistence.service;

import com.packt.springsecurity.backend.model.Account;

public interface IAccountService {

    // read

    public Account findByName(final String name);

    public Account findById(final long id);

    // write

    public Account create(final Account entity);

    public void update(final Account entity);

}
