package com.carbuying.car_buying_app.models.entity;

import com.carbuying.car_buying_app.models.enums.OfferStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_offers",
        indexes = {
                @Index(name = "idx_supplier_id", columnList = "supplier_id"),
                @Index(name = "idx_request_id", columnList = "request_id"),
                @Index(name = "idx_status", columnList = "status"),
                @Index(name = "idx_created_at", columnList = "created_at")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_supplier_request", columnNames = {"supplier_id", "request_id"})
        })
public class SupplierOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private CustomerRequest customerRequest;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus status = OfferStatus.PENDING;

    @DecimalMin(value = "0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999999.99", message = "Price cannot exceed 999,999,999.99")
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal price;

    @Size(max = 2000)
    @Column(name = "car_details", columnDefinition = "TEXT")
    private String carDetails;

    @Column(name = "inspection_score")
    private Integer inspectionScore;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public SupplierOffer() {}

    public SupplierOffer(Long supplierId, CustomerRequest customerRequest, BigDecimal price, String carDetails) {
        this.supplierId = supplierId;
        this.customerRequest = customerRequest;
        this.price = price;
        this.carDetails = carDetails;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public CustomerRequest getCustomerRequest() { return customerRequest; }
    public void setCustomerRequest(CustomerRequest customerRequest) { this.customerRequest = customerRequest; }

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
