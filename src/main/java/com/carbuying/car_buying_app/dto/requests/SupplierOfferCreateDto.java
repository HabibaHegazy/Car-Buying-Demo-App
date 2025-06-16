package com.carbuying.car_buying_app.dto.requests;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class SupplierOfferCreateDto {

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Request ID is required")
    private Long requestId;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Price cannot exceed 999,999,999.99")
    private BigDecimal price;

    @Size(max = 2000, message = "Car details cannot exceed 2000 characters")
    private String carDetails;

    public SupplierOfferCreateDto() {}

    public SupplierOfferCreateDto(Long supplierId, Long requestId, BigDecimal price, String carDetails) {
        this.supplierId = supplierId;
        this.requestId = requestId;
        this.price = price;
        this.carDetails = carDetails;
    }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCarDetails() { return carDetails; }
    public void setCarDetails(String carDetails) { this.carDetails = carDetails; }

}
