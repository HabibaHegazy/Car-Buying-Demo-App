package com.carbuying.car_buying_app.repositories;

import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {

    Page<CustomerRequest> findByStatus(RequestStatus status, Pageable pageable);

//    Page<CustomerRequest> findByCustomerId(Long customerId, Pageable pageable);

//    @Query("SELECT cr FROM CustomerRequest cr LEFT JOIN FETCH cr.offers WHERE cr.id = :id")
//    CustomerRequest findByIdWithOffers(@Param("id") Long id);

//    @Query("SELECT COUNT(o) FROM SupplierOffer o WHERE o.customerRequest.id = :requestId")
//    int countOffersByRequestId(@Param("requestId") Long requestId);

}
