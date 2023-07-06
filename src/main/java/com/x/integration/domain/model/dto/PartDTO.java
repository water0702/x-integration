package com.x.integration.domain.model.dto;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;

@JmixEntity(name = "INTEG_PartDTO")
public class PartDTO {

    @InstanceName
    private String partNumber;

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
}