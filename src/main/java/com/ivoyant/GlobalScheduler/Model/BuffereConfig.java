package com.ivoyant.GlobalScheduler.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BuffereConfig {
    @Id
    @Column(name = "id", nullable = false)
    private long scheduleId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("target")
    private String target;
    @JsonProperty("active")
    private boolean active = false;
    @JsonProperty("targetType")
    private String targetType;
    @JsonProperty("cron_expression")
    private String cron_expression;
    @JsonProperty("createdTime")
    private LocalDateTime createdTime;
    @JsonProperty("lastRun")
    private LocalDateTime lastRun;
    @JsonProperty("nextRun")
    private LocalDateTime nextRun;
    @JsonProperty("scheduleDescription")
    private String scheduleDescription;
    @JsonProperty("state")
    private SchedulerState state ;
}
