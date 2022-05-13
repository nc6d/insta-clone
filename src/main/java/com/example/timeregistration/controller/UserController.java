package com.example.timeregistration.controller;

import com.example.timeregistration.dto.SaveRecordDto;
import com.example.timeregistration.model.TimeRecord;
import com.example.timeregistration.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    RecordService recordService;
    ModelMapper modelMapper;

    @Autowired
    public UserController(RecordService recordService, ModelMapper modelMapper) {
        this.recordService = recordService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/save/record")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<TimeRecord> testRequest(@RequestBody SaveRecordDto dto){
        TimeRecord result = recordService.saveRecord(modelMapper.map(dto,TimeRecord.class));
        return ResponseEntity.ok(result);
    }

}
