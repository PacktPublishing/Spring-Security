package com.packt.springsecurity.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.packt.springsecurity.backend.model.Account;

public interface IAccountJpaDAO extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Account findByUsername(final String username);

}
