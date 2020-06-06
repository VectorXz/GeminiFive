package com.example.gemini5;

import com.example.gemini5.Model.SciencePlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SciencePlanRepository extends CrudRepository<SciencePlan, Integer> {
    List<SciencePlan> findByStatus(String status);
}
