/*
 * Copyright (c) 2020. Chaiyong Ragkhitwetsagul
 * All rights reserved.
 */

package edu.gemini.app.ocs.model;

import java.util.Date;

public class SpecialEquipment {
    private String equipmentName;
    private String ownerName;
    private Date installedDate;

    public SpecialEquipment(String equipmentName, String ownerName, Date installedDate) {
        this.equipmentName = equipmentName;
        this.ownerName = ownerName;
        this.installedDate = installedDate;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
    }
}
