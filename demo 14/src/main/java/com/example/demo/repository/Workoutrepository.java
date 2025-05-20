package com.example.demo.repository;

import com.example.demo.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Workoutrepository extends JpaRepository<Workout, Integer> {

    @Query("SELECT w FROM Workout w WHERE w.user = ?#{authentication.name}")
    List<Workout> findAllByUser();
}
