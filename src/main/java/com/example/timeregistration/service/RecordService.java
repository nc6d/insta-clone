package com.example.timeregistration.service;

import com.example.timeregistration.model.TimeRecord;
import com.example.timeregistration.repo.TimeRecordRepository;
import com.example.timeregistration.repo.UserRepository;
import com.example.timeregistration.security.jwt.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import com.example.timeregistration.model.User;

import java.util.NoSuchElementException;

@Service
public class RecordService {

    UserRepository userRepository;
    TimeRecordRepository timeRecordRepository;

    public RecordService(UserRepository userRepository, TimeRecordRepository timeRecordRepository) {
        this.userRepository = userRepository;
        this.timeRecordRepository = timeRecordRepository;
    }

    public TimeRecord saveRecord(TimeRecord timeRecord) {
        JwtUser o = ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User userRepo = userRepository.findByEmail(o.getEmail()).orElseThrow(()->new NoSuchElementException("User autintificstion error"));
        timeRecord.setUser(userRepo);
        return timeRecordRepository.save(timeRecord);

    }
}
