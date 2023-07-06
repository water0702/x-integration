package com.x.integration.domain.model.dto;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;

@JmixEntity(name = "INTEG_BomDTO")
public class BomDTO {

    @InstanceName
    private String bomName;

    public String getBomName() {
        return bomName;
    }

    public void setBomName(String bomName) {
        this.bomName = bomName;
    }
}