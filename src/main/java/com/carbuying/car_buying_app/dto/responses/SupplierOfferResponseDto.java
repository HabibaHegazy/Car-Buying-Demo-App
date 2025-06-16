package com.carbuying.car_buying_app.dto.responses;

import com.carbuying.car_buying_app.models.enums.OfferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SupplierOfferResponseDto {

    private Long id;
    private Long supplierId;
    private Long requestId;
    private OfferStatus status;
    private BigDecimal price;
    private String carDetails;
    private Integer inspectionScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public OfferStatus getStatus() { return status; }
    public void setStatus(OfferStatus status) { this.status = status; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCarDetails() { return carDetails; }
    public void setCarDetails(String carDetails) { this.carDetails = carDetails; }

    public Integer getInspectionScore() { return inspectionScore; }
    public void setInspectionScore(Integer inspectionScore) { this.inspectionScore = inspectionScore; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
