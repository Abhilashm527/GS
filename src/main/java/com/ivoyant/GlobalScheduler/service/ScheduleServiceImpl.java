package com.ivoyant.GlobalScheduler.service;

import com.ivoyant.GlobalScheduler.Model.Config;
import com.ivoyant.GlobalScheduler.Repo.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import org.quartz.CronExpression;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    public ScheduleRepo scheduleRepo;


    @Override
    public Config create(Config config) {
        config.setCreatedTime(LocalDateTime.now());
        String expression = config.getCron_expression();
        try {
            CronExpression cron = new CronExpression(expression);
            Date nextRun = cron.getNextValidTimeAfter(new Date());
            LocalDateTime nextRunDateTime = nextRun.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            System.out.println("Next run: " + nextRunDateTime);
            config.setNextRun(nextRunDateTime);
        }
        catch (Exception e)
        {
            System.out.println("Invalid Cron expression");
        }

        return scheduleRepo.save(config);
    }
    @Override
    public Optional<Config> getSchedule(int id) {
        Optional<Config> config=scheduleRepo.findById(id);
        String expression = config.get().getCron_expression();
//        CronExpression cronTrigger = CronExpression.parse(expression);
//        LocalDateTime next = cronTrigger.next(LocalDateTime.now());
//        config.get().setNextRun(next);
//        Config config1 = config.get();
//        scheduleRepo.save(config1);
        return scheduleRepo.findById(id);
    }

    @Override
    public void deleteSchedule(int id) {
       scheduleRepo.deleteById(id);
    }


    @Override
    public ResponseEntity reschedule(Config config) {
    Optional<Config> config1 = scheduleRepo.findById(config.getScheduleId());
    return ResponseEntity.ok(config1);
    }

    @Override
    public ResponseEntity<String> updateSchedule(Config config) {
       if(!scheduleRepo.existsById(config.getScheduleId()))
       {
            scheduleRepo.save(config);
            return ResponseEntity.ok("The given schedule was not present so its added");
        }
        else
        {
            Optional<Config> config1 = scheduleRepo.findById(config.getScheduleId());
            config1.get().setState(config.getState());
            config1.get().setCron_expression(config.getCron_expression());
            config1.get().setName(config.getName());
            config1.get().setLastRun(config.getLastRun());
            config1.get().setNextRun(config.getNextRun());
            config1.get().setCreatedTime(config.getCreatedTime());
            config1.get().setTarget(config.getTarget());
            config1.get().setTargetType(config.getTargetType());
            config1.get().setActive(config.isActive());
            scheduleRepo.save(config1.get());
            return ResponseEntity.ok("The given schedule is updated");
        }
    }

    @Override
    public List<Config> getAllSchedule() {
        return scheduleRepo.findAll();
    }

}
