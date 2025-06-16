package com.carbuying.car_buying_app.repositories;

import com.carbuying.car_buying_app.models.entity.SupplierOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierOfferRepository extends JpaRepository<SupplierOffer, Long> {

    List<SupplierOffer> findByCustomerRequestId(Long requestId);

    Page<SupplierOffer> findBySupplierId(Long supplierId, Pageable pageable);

//    @Query("SELECT so FROM SupplierOffer so WHERE so.supplierId = :supplierId AND so.customerRequest.id = :requestId")
//    Optional<SupplierOffer> findBySupplierIdAndRequestId(@Param("supplierId") Long supplierId, @Param("requestId") Long requestId);

    boolean existsBySupplierIdAndCustomerRequestId(Long supplierId, Long requestId);

}
