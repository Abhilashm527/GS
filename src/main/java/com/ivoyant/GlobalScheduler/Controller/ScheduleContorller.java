package com.ivoyant.GlobalScheduler.Controller;

import com.ivoyant.GlobalScheduler.Model.Config;
import com.ivoyant.GlobalScheduler.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/globalScheduler/api")
@Api(value = "Schedule API")
public class ScheduleContorller {
    @Autowired
    public ScheduleService scheduleService;

    @ApiOperation(value = "Create a new schedule")
    @PostMapping("/createschedule")
    public ResponseEntity schedule(@RequestBody Config config) {
        scheduleService.create(config);
        return ResponseEntity.ok("Config has been created");
    }


    @ApiOperation(value = "Get a schedule by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the schedule"),
            @ApiResponse(code = 404, message = "The given ID doesn't have any schedule configurations")
    })
    @GetMapping("/createschedule/{id}")
    public ResponseEntity getScheduleById(@PathVariable(name = "id") int id) {
        Optional<Config> config = scheduleService.getSchedule(id);
        if (config.isEmpty())
            return ResponseEntity.ok("The given id doesn't have any Schedule configs");
        return ResponseEntity.ok(config);
    }


    @ApiOperation(value = "Delete a schedule by ID")
    @DeleteMapping("/createschedule/{id}")
    public ResponseEntity deleteSchedule(@PathVariable(name = "id") int id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("the Schedule have been deleted");
    }


    @ApiOperation(value = "Update a schedule")
    @PutMapping("updateschedule")
    public ResponseEntity updateSchedule(@RequestBody Config config) {
        return scheduleService.updateSchedule(config);
    }

    @ApiOperation(value = "Get all schedules")
    @GetMapping("/getAll")
    public ResponseEntity getAllSchedule() {
        return ResponseEntity.ok(scheduleService.getAllSchedule());
    }

}
