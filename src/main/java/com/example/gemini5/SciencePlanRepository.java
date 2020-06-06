package com.example.gemini5;

import com.example.gemini5.Model.SciencePlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SciencePlanRepository extends CrudRepository<SciencePlan, Integer> {
}
