package com.ivoyant.GlobalScheduler.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Config {
    @Id
    @Column(name = "scheduleid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleId;
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
    @JsonProperty("zoneId")
    private String zoneId;
    @JsonProperty("createdTime")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createdTime;
    @JsonProperty("lastRun")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date lastRun;
    @JsonProperty("nextRun")
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date nextRun;
    @JsonProperty("state")
    private SchedulerState state = SchedulerState.CREATED;

}
