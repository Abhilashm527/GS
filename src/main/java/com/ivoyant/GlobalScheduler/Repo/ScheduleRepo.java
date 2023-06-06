package com.ivoyant.GlobalScheduler.Repo;

import com.ivoyant.GlobalScheduler.Model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Config,Integer> {
}
