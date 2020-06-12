package com.example.gemini5.Repository;

import com.example.gemini5.Model.SciencePlan;
import edu.gemini.app.ocs.model.BaseSciencePlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SciencePlanRepository extends CrudRepository<SciencePlan, Integer> {
    public List<SciencePlan> findByStatus(BaseSciencePlan.STATUS status);
    public SciencePlan findByPlanId(Integer id);
    public List<SciencePlan> findByStartDate(Date startDate);
    public List<SciencePlan> findByEndDate(Date endDate);
}
