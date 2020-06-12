package com.example.gemini5;

import com.example.gemini5.Model.SciencePlan;
import edu.gemini.app.ocs.model.BaseSciencePlan;

public interface PlanCreator {
    public BaseSciencePlan toBaseSciencePlan(SciencePlan sciencePlan);
}
