package com.ivoyant.GlobalScheduler.util;

import com.ivoyant.GlobalScheduler.Model.Config;
import com.ivoyant.GlobalScheduler.Model.SchedulerState;
import com.ivoyant.GlobalScheduler.service.ScheduleServiceImpl;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduleTask {

    private final TaskScheduler taskScheduler;

    @Autowired
    public ScheduleTask(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Autowired
    private ScheduleServiceImpl scheduleService;

    @Autowired
    private DateModifier dateModifier;

    @Scheduled(fixedRate = 10000)
    public void executeTask() {
        List<Config> config = scheduleService.getAllSchedule();
        for(Config config1 : config)
        {
                String expression = config1.getCron_expression();
                try {
                    CronExpression cron = new CronExpression(expression);
                    Date nextRun = cron.getNextValidTimeAfter(new Date());
                    Date nextRunDateTime = dateModifier.formatDateOnZone(nextRun, config1.getZoneId());
                    System.out.println("Next run: " + nextRunDateTime);
                    config1.setNextRun(nextRunDateTime);
                } catch (Exception e) {
                    System.out.println("Invalid Cron expression");
                }
                scheduleService.updateSchedule(config1);
        }
        System.out.println("Executing task...");
    }
}
