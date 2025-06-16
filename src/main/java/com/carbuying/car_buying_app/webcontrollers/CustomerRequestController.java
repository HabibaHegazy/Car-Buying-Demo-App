package com.carbuying.car_buying_app.webcontrollers;

import com.carbuying.car_buying_app.dto.requests.CustomerRequestCreateDto;
import com.carbuying.car_buying_app.dto.responses.CustomerRequestResponseDto;
import com.carbuying.car_buying_app.models.enums.RequestStatus;
import com.carbuying.car_buying_app.services.CustomerRequestService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer-requests")
public class CustomerRequestController {

    private final CustomerRequestService customerRequestService;

    @PostMapping
    public ResponseEntity<CustomerRequestResponseDto> createRequest(@Valid @RequestBody CustomerRequestCreateDto createDto) {
        CustomerRequestResponseDto response = customerRequestService.createRequest(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerRequestResponseDto>> getAllRequests(@RequestParam(required = false) RequestStatus status,
                                                                           @PageableDefault(size = 20) Pageable pageable) {

        Page<CustomerRequestResponseDto> requests;
        if (status != null) {
            requests = customerRequestService.getRequestsByStatus(status, pageable);
        } else {
            requests = customerRequestService.getAllRequests(pageable);
        }

        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRequestResponseDto> getRequestById(@PathVariable Long id) {
        CustomerRequestResponseDto request = customerRequestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CustomerRequestResponseDto> updateRequestStatus(@PathVariable Long id,
                                                                          @RequestParam RequestStatus status) {
        CustomerRequestResponseDto updatedRequest = customerRequestService.updateRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }

}
