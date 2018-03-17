/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger.service;

import com.klepra.dailylogger.model.Log;
import com.klepra.dailylogger.model.User;
import com.klepra.dailylogger.repository.LogRepository;
import com.klepra.dailylogger.repository.UserRepository;
import com.klepra.dailylogger.utils.DateUtils;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author klemen
 */
@Service
public class LogService {

    private LogRepository logRepository;

    private UserRepository userRepository;

    @Autowired
    public LogService(LogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    public List<Log> getLogsByUserAndDate(String username, Date date) throws ParseException {

        //another way to get loged in user:
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //return this.logRepository.findAllByUserForDay(DateUtils.getDateZeroTime(date), DateUtils.getTommorowsDateMidnight(), user);
        User user = userRepository.findOneByUsername(username);
        return user.getLogs();
    }

    @Transactional
    public void deleteLogByIdAndUser(Long id, String username) {
        User user = this.userRepository.findOneByUsername(username); 
        this.logRepository.deleteByIdandUser(id, user);
    }

    //persist/update log
    public Log saveLogForUser(Log log, String username) {
        User user = userRepository.findOneByUsername(username);
        log.setUser(user);
        return this.logRepository.save(log);
    }
}
