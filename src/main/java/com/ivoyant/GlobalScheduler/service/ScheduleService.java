package com.ivoyant.GlobalScheduler.service;

import com.ivoyant.GlobalScheduler.Model.Config;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Config create(Config config);

    Optional<Config> getSchedule(int id);

    void deleteSchedule(int id);

    ResponseEntity reschedule(Config config);


    ResponseEntity updateSchedule(Config config);

    List<Config> getAllSchedule();
}
