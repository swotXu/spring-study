package com.swotxu.demo2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swotxu.demo2.entity.UserJpa;

@Repository
public interface UserJpaDao extends JpaRepository<UserJpa, Integer>{

}
