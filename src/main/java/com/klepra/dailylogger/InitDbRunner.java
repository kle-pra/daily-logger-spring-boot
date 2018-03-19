/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger;

import com.klepra.dailylogger.model.Log;
import com.klepra.dailylogger.model.User;
import com.klepra.dailylogger.repository.LogRepository;
import com.klepra.dailylogger.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author klemen
 */
@Component
public class InitDbRunner implements CommandLineRunner {

    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public void run(String... strings) throws Exception {

        User user = new User("user", bcryptEncoder.encode("password"));
        userRepository.save(user);
        Log log1 = new Log(new Date(), "Learn about Spring Boot 2.0");
        Log log2 = new Log(new Date(), "2 hour hike");
        log1.setUser(user);
        log2.setUser(user);
        logRepository.save(log1);
        logRepository.save(log2);

        User admin = new User("admin", bcryptEncoder.encode("password"));
        userRepository.save(admin);
        Log log3 = new Log(new Date(), "Learn about UNIX");
        Log log4 = new Log(new Date(), "Beerpong");
        log3.setUser(admin);
        log4.setUser(admin);
        logRepository.save(log3);
        logRepository.save(log4);
    }

}
