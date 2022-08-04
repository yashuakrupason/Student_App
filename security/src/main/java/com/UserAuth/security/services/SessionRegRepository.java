package com.UserAuth.security.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SessionRegRepository extends  CrudRepository<SessionRegistry,String>{

}
