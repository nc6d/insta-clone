package com.example.timeregistration.repo;

import com.example.timeregistration.model.TimeRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRecordRepository extends MongoRepository<TimeRecord, Long> {

}
