package com.x.integration.domain.model.dto;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Entity;

@JmixEntity(name = "INTEG_WorkOrderDTO")
public class WorkOrderDTO {

    @InstanceName
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}