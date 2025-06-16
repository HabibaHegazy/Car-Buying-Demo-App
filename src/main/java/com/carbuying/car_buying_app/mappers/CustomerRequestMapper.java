package com.carbuying.car_buying_app.mappers;

import com.carbuying.car_buying_app.dto.requests.CustomerRequestCreateDto;
import com.carbuying.car_buying_app.dto.responses.CustomerRequestResponseDto;
import com.carbuying.car_buying_app.models.entity.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    CustomerRequest toEntity(CustomerRequestCreateDto dto);

    @Mapping(target = "offersCount", expression = "java(entity.getOffersCount())")
    CustomerRequestResponseDto toResponseDto(CustomerRequest entity);

}
