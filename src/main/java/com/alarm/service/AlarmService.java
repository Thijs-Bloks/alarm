package com.alarm.service;

import com.alarm.model.Alarm;
import com.alarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class AlarmService {
    @Autowired
    AlarmRepository alarmRepository;

    public Iterable<Alarm> getAlarms() {
        return alarmRepository.findAll();
    }

    public Future<String> postAlarm(Alarm alarm) {
        try {
            Thread.sleep(5000);
            alarmRepository.save(alarm);
            return new AsyncResult<String>("Added the alarm!");
        } catch (InterruptedException e) {
            return new AsyncResult<String>("Whoops error!");
        }
    }

    @Transactional()
    public String deleteAlarm(Long id) {
        if (alarmRepository.findById(id).isPresent()) {
            alarmRepository.deleteById(id);
            return "We deleted your alarm";
        }
        return "No alarm found with that id";
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Scheduled(cron = "1 9-17 * * * ?")
//    public void dataLogger() {
//        System.out.println("logger: " + alarmRepository.findAll().toString());
//    }
}
