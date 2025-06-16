package com.carbuying.car_buying_app.dto.requests;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerRequestCreateDto {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Size(max = 2000, message = "Description cannot exceed 2000 characters")
    private String description;

    @NotNull(message = "Inspection company is required")
    private InspectionCompany checkedByCompany;

    public CustomerRequestCreateDto() {}

    public CustomerRequestCreateDto(Long customerId, String description, InspectionCompany checkedByCompany) {
        this.customerId = customerId;
        this.description = description;
        this.checkedByCompany = checkedByCompany;
    }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public InspectionCompany getCheckedByCompany() { return checkedByCompany; }
    public void setCheckedByCompany(InspectionCompany checkedByCompany) { this.checkedByCompany = checkedByCompany; }

}
