package com.ivoyant.GlobalScheduler.util;

import com.ivoyant.GlobalScheduler.Model.Config;
import com.ivoyant.GlobalScheduler.Model.SchedulerState;
import com.ivoyant.GlobalScheduler.service.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduleProvider {


    private final TaskScheduler taskScheduler;

    @Autowired
    public ScheduleProvider(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private RestExample restExample;

    @Autowired
    private DateModifier dateModifier;

    @Scheduled(fixedRate = 1000)
    public void executeTask() {
        List<Config> config = scheduleService.getAllSchedule();

        for(Config config1 : config) {
            if (SchedulerState.CREATED == config1.getState()) {
                try {
                    Date date = config1.getNextRun();
                    Date bufferedTime = dateModifier.formatDateOnZone(new Date(), config1.getZoneId());
                    int value = bufferedTime.compareTo(date);
                    System.out.println("int value " + value);
                    if (value >= 0) {
                        config1.setState(SchedulerState.BUFFERED);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid Cron expression");
                }
                scheduleService.updateSchedule(config1);
            }
            else if(SchedulerState.BUFFERED == config1.getState())
            {
                Date date = config1.getNextRun();
                Date bufferedTime = dateModifier.formatDateOnZone(new Date(), config1.getZoneId());
                if(date.compareTo(bufferedTime) >= 0)
                {
                    config1.setState(SchedulerState.RUNNING);
                    ResponseEntity<String> data = restExample.restCall(config1.getTarget(), HttpMethod.valueOf(config1.getTargetType()));
                    config1.setLastRun(dateModifier.formatDateOnZone(new Date(), config1.getZoneId()));
                    System.out.println("Resonse"+data.getBody());
                    config1.setState(SchedulerState.COMPLETED);

                }
                scheduleService.updateSchedule(config1);
            } else if (SchedulerState.COMPLETED == config1.getState()) {
                if(dateModifier.formatDateOnZone(new Date(), config1.getZoneId()).compareTo(config1.getNextRun()) >= 0) {
                    config1.setState(SchedulerState.CREATED);
                }
                scheduleService.updateSchedule(config1);
            }
        }

        System.out.println("Executing scheduleprovide...");
    }
}
