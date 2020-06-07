/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.example;

import edu.gemini.app.ocs.model.BaseSciencePlan;

public class MySciencePlan extends BaseSciencePlan {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
