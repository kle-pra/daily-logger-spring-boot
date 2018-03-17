/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klepra.dailylogger.controller;

import com.klepra.dailylogger.model.Log;
import com.klepra.dailylogger.service.LogService;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author klemen
 */
@RestController
@RequestMapping("/api/logs")
public class LogController {

    private LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @RequestMapping(method = GET)
    public List<Log> list(@RequestParam Date date, Principal principal) throws ParseException {
        return this.logService.getLogsByUserAndDate(principal.getName(), date);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<Log> put(@PathVariable String id, @RequestBody Log log, Principal principal) {
        log.setId(Long.parseLong(id));
        Log updatedLog = this.logService.saveLogForUser(log, principal.getName());
        return ResponseEntity.ok(updatedLog);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<Log> post(@RequestBody Log log, Principal principal) {
        Log savedLog = this.logService.saveLogForUser(log, principal.getName());
        return ResponseEntity.ok(savedLog);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<?> delete(@PathVariable String id, Principal principal) {
        this.logService.deleteLogByIdAndUser(Long.parseLong(id), principal.getName());
        return ResponseEntity.noContent().build();
    }
}
