package com.klepra.dailylogger.service;

import com.klepra.dailylogger.model.Log;
import com.klepra.dailylogger.model.User;
import com.klepra.dailylogger.repository.LogRepository;
import com.klepra.dailylogger.repository.UserRepository;
import com.klepra.dailylogger.utils.DateUtils;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author klemen
 */
@Service
public class LogService {

    private final LogRepository logRepository;

    private final UserRepository userRepository;

    @Autowired
    public LogService(LogRepository logRepository, UserRepository userRepository) {
        this.logRepository = logRepository;
        this.userRepository = userRepository;
    }

    public List<Log> getLogsByUserAndDate(String username, Date date) throws ParseException {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByUsername(username);
        return this.logRepository.findAllByUserForDay(DateUtils.getDateZeroTime(date), DateUtils.getTommorowsDateMidnight(), user);
        //alternative way:
//        return user.getLogs();
    }

    @Transactional
    public void deleteLogByIdAndUser(Long id, String username) {
        User user = this.userRepository.findOneByUsername(username);
        this.logRepository.deleteByIdandUser(id, user);
    }

    public Log saveLogForUser(Log log, String username) {
        User user = userRepository.findOneByUsername(username);
        log.setUser(user);
        return this.logRepository.save(log);
    }
}
