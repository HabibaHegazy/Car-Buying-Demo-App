package com.carbuying.car_buying_app.services;

import com.carbuying.car_buying_app.dto.requests.SupplierOfferCreateDto;
import com.carbuying.car_buying_app.dto.responses.SupplierOfferResponseDto;
import com.carbuying.car_buying_app.mappers.SupplierOfferMapper;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import com.carbuying.car_buying_app.models.entity.SupplierOffer;
import com.carbuying.car_buying_app.repositories.SupplierOfferRepository;
import com.carbuying.car_buying_app.services.inspection.InspectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierOfferServiceImpl implements SupplierOfferService {

    private final SupplierOfferRepository supplierOfferRepository;
    private final SupplierOfferMapper supplierOfferMapper;
    private final CustomerRequestServiceImpl customerRequestServiceImpl;
    private final InspectionService inspectionService;

    @Override
    public SupplierOfferResponseDto submitOffer(SupplierOfferCreateDto createDto) {
        CustomerRequest customerRequest = customerRequestServiceImpl.findActiveRequestById(createDto.getRequestId());

        if (supplierOfferRepository.existsBySupplierIdAndCustomerRequestId(
                createDto.getSupplierId(), createDto.getRequestId())) {
            throw new RuntimeException("Supplier already has an offer for this request");
        }

        SupplierOffer offer = supplierOfferMapper.toEntity(createDto);
        offer.setCustomerRequest(customerRequest);
        SupplierOffer savedOffer = supplierOfferRepository.save(offer);

        try {
            int inspectionScore = inspectionService.requestInspection(
                    customerRequest.getCheckedByCompany(),
                    savedOffer.getId(),
                    savedOffer.getCarDetails()
            );
            savedOffer.setInspectionScore(inspectionScore);
            savedOffer = supplierOfferRepository.save(savedOffer);
        } catch (Exception e) {
            System.err.println("Failed to request inspection: " + e.getMessage());
        }

        return supplierOfferMapper.toResponseDto(savedOffer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierOfferResponseDto> getOffersByRequestId(Long requestId) {
        return supplierOfferRepository.findByCustomerRequestId(requestId)
                .stream()
                .map(supplierOfferMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupplierOfferResponseDto> getOffersBySupplierId(Long supplierId, Pageable pageable) {
        return supplierOfferRepository.findBySupplierId(supplierId, pageable)
                .map(supplierOfferMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierOfferResponseDto getOfferById(Long id) {
        SupplierOffer offer = supplierOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier offer not found with id: " + id));
        return supplierOfferMapper.toResponseDto(offer);
    }

}
