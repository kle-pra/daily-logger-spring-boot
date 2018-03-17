/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger.repository;

import com.klepra.dailylogger.model.Log;
import com.klepra.dailylogger.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author klemen
 */
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("Select l from Log l where date > ?1 and date < ?2 and user = ?3")
    List<Log> findAllByUserForDay(Date from, Date to, User user);

    @Modifying
    @Query("delete from Log l where l.id = ?1 and l.user = ?2")
    void deleteByIdandUser(Long id, User user);
}
