package com.example.gemini5.Repository;

import com.example.gemini5.Model.SciencePlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SciencePlanRepository extends CrudRepository<SciencePlan, Integer> {
    public List<SciencePlan> findByStatus(String status);
    public SciencePlan findByPlanId(Integer id);
}
