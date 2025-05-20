package com.example.demo.service;

import com.example.demo.model.Workout;
import com.example.demo.repository.Workoutrepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final Workoutrepository workoutrepository;

    public WorkoutService(Workoutrepository workoutrepository){
        this.workoutrepository = workoutrepository;
    }

    @PreAuthorize("#workout.user == authenticaion.name")
    public void saveWorkout(Workout workout){
        workoutrepository.save(workout);
    }

    public List<Workout> findWorkouts(){
        return workoutrepository.findAllByUser();
    }

    public void deleteWorkout(Integer id){
        workoutrepository.deleteById(id);
    }
}
