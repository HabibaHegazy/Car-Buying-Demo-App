package com.carbuying.car_buying_app.webcontrollers;

import com.carbuying.car_buying_app.dto.requests.SupplierOfferCreateDto;
import com.carbuying.car_buying_app.dto.responses.SupplierOfferResponseDto;
import com.carbuying.car_buying_app.services.SupplierOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier-offers")
public class SupplierOfferController {

    private final SupplierOfferService supplierOfferService;

    @PostMapping
    public ResponseEntity<SupplierOfferResponseDto> submitOffer(@Valid @RequestBody SupplierOfferCreateDto createDto) {
        SupplierOfferResponseDto response = supplierOfferService.submitOffer(createDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/by-request/{requestId}")
    public ResponseEntity<List<SupplierOfferResponseDto>> getOffersByRequestId(@PathVariable Long requestId) {
        List<SupplierOfferResponseDto> offers = supplierOfferService.getOffersByRequestId(requestId);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<Page<SupplierOfferResponseDto>> getOffersBySupplierId(@PathVariable Long supplierId,
                                                                                @PageableDefault(size = 20) Pageable pageable) {

        Page<SupplierOfferResponseDto> offers = supplierOfferService.getOffersBySupplierId(supplierId, pageable);
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierOfferResponseDto> getOfferById(@PathVariable Long id) {
        SupplierOfferResponseDto offer = supplierOfferService.getOfferById(id);
        return ResponseEntity.ok(offer);
    }

}
