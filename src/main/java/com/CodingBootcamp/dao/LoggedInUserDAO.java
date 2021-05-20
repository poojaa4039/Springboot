package com.CodingBootcamp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingBootcamp.model.LoggedInUsers;

public interface LoggedInUserDAO  extends JpaRepository<LoggedInUsers, Long>{

}
