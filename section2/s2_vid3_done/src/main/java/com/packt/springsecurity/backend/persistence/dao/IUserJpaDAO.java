package com.packt.springsecurity.backend.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.packt.springsecurity.backend.persistence.model.User;

public interface IUserJpaDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByName(final String name);

}
