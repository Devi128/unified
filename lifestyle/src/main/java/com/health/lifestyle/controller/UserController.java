package com.health.lifestyle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.lifestyle.dto.TrackRequest;
import com.health.lifestyle.dto.UnifiedLifestyleResponse;
import com.health.lifestyle.model.DailyHealthLog;
import com.health.lifestyle.model.User;
import com.health.lifestyle.repository.UserRepository;
import com.health.lifestyle.service.RecommendationService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendationService recommendationService;

    // ✅ Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // ✅ Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get Unified Plan
    @GetMapping("/{id}/unified-plan")
    public UnifiedLifestyleResponse getUnifiedPlan(@PathVariable String id) {
        return recommendationService.getUnifiedPlan(id);
    }

    // ✅ Track Daily Progress (ONLY ONE METHOD)
    @PostMapping("/{id}/track")
    public DailyHealthLog trackHealth(
            @PathVariable String id,
            @RequestBody TrackRequest request) {

        return recommendationService.trackDailyProgress(
                id,
                request.getFoodsConsumed(),
                request.getExercisesDone(),
                request.getSleepHours()
        );
    }
}