package com.carbuying.car_buying_app.dto.responses;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import com.carbuying.car_buying_app.models.enums.RequestStatus;

import java.time.LocalDateTime;

public class CustomerRequestResponseDto {

    private Long id;
    private Long customerId;
    private RequestStatus status;
    private String description;
    private InspectionCompany checkedByCompany;
    private int offersCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public InspectionCompany getCheckedByCompany() { return checkedByCompany; }
    public void setCheckedByCompany(InspectionCompany checkedByCompany) { this.checkedByCompany = checkedByCompany; }

    public int getOffersCount() { return offersCount; }
    public void setOffersCount(int offersCount) { this.offersCount = offersCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}