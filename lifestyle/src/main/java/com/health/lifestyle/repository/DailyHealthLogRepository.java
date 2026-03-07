package com.health.lifestyle.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.health.lifestyle.model.DailyHealthLog;
public interface DailyHealthLogRepository 
        extends MongoRepository<DailyHealthLog, String> {
                List<DailyHealthLog> findByUserIdAndDateBetween(
        String userId,
        LocalDate startDate,
        LocalDate endDate);
}


