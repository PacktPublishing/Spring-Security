package com.packt.springsecurity.backend.persistence.service;

import com.packt.springsecurity.backend.model.Possession;

public interface IPossessionService {

    // read

    public Possession findByName(final String name);

    public Possession findById(final long id);

    // write

    public Possession create(final Possession entity);

    public void update(final Possession entity);

}
