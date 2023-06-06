package com.ivoyant.GlobalScheduler.Repo;

import com.ivoyant.GlobalScheduler.Model.BuffereConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BufferRepo extends JpaRepository<BuffereConfig,Integer> {

}
