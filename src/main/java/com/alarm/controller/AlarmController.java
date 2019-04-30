package com.alarm.controller;

import com.alarm.model.Alarm;
import com.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/alarms")
public class AlarmController {

    @Autowired
    AlarmService alarmService;

    /**
     * Get all the available alarms
     * @return Iterable Alarm
     */
    @GetMapping()
    public Iterable<Alarm> getAlarms() {
        return alarmService.getAlarms();
    }

    /**
     * Post the alarm object
     * @param alarm object
     * @return
     */
    @PostMapping()
    public Future<String> postAlarm(@RequestBody() Alarm alarm) {
        return alarmService.postAlarm(alarm);
    }

    /**
     * Delete a book by isbn
     * @param id
     * @return
     */
    @DeleteMapping(value = "/alarm")
    public String deleteBook(@RequestParam(name = "id") Long id) {
        return alarmService.deleteAlarm(id);
    }
}
