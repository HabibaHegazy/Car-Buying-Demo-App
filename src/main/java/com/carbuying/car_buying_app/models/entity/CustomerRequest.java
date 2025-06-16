package com.carbuying.car_buying_app.models.entity;

import com.carbuying.car_buying_app.models.enums.InspectionCompany;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_requests", indexes = {
        @Index(name = "idx_customer_id", columnList = "customer_id"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_created_at", columnList = "created_at")
})
public class CustomerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.ACTIVE;

    @Size(max = 2000)
    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "checked_by_company", nullable = false)
    private InspectionCompany checkedByCompany;

    @OneToMany(mappedBy = "customerRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SupplierOffer> offers = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public CustomerRequest() {}

    public CustomerRequest(Long customerId, String description, InspectionCompany checkedByCompany) {
        this.customerId = customerId;
        this.description = description;
        this.checkedByCompany = checkedByCompany;
    }

    // Getters and Setters
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

    public List<SupplierOffer> getOffers() { return offers; }
    public void setOffers(List<SupplierOffer> offers) { this.offers = offers; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // Helper methods
    public int getOffersCount() {
        return offers != null ? offers.size() : 0;
    }

    public boolean isActive() {
        return status == RequestStatus.ACTIVE;
    }

}
