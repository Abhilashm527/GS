package com.ivoyant.GlobalScheduler.Controller;

import com.ivoyant.GlobalScheduler.service.BufferedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BufferedController {


    @Autowired
    private BufferedService bufferedService;

    @GetMapping("/bufferedService/{id}")
    public ResponseEntity getDatabyID(@PathVariable int id)
    {
        return ResponseEntity.ok(bufferedService.getById(id));
    }
}
