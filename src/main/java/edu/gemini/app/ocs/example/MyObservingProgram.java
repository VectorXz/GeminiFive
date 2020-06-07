/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.example;

import edu.gemini.app.ocs.model.BaseObservingProgram;

public class MyObservingProgram extends BaseObservingProgram {
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
