package com.ivoyant.GlobalScheduler.service;

import com.ivoyant.GlobalScheduler.Model.BuffereConfig;
import com.ivoyant.GlobalScheduler.Repo.BufferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BufferedServiceImpl implements   BufferedService{
    @Autowired
    private BufferRepo bufferRepo;

    public void addBufferedConfig(BuffereConfig buffereConfig)
    {
        bufferRepo.save(buffereConfig);
    }

    @Override
    public Object getById(int id) {
        return bufferRepo.findById(id);
    }
}
