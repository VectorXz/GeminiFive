package com.example.gemini5;

import com.example.gemini5.Model.SciencePlan;
import com.example.gemini5.Model.SciencePlanAdapter;
import edu.gemini.app.ocs.model.BaseSciencePlan;

public class SciencePlanCreator {
    private SciencePlanAdapter adapter = new SciencePlanAdapter();

    public BaseSciencePlan create(SciencePlan sciencePlan) {
        System.out.println("Creating...1");
        return this.adapter.toBaseSciencePlan(sciencePlan);
    }
}
