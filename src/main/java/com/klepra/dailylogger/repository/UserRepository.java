/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger.repository;

import com.klepra.dailylogger.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author klemen
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findOneByUsername(String username);   
}
