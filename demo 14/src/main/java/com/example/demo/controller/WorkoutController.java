package com.example.demo.controller;

import com.example.demo.model.Workout;
import com.example.demo.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/")
    public void add(@RequestBody Workout workout){
        workoutService.saveWorkout(workout);
    }

    @GetMapping("/")
    public List<Workout> finaAll(){
        return workoutService.findWorkouts();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        workoutService.deleteWorkout(id);
    }
}
