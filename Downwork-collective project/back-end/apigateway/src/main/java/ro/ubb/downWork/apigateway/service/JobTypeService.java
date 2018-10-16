package ro.ubb.downWork.apigateway.service;

import ro.ubb.downWork.apigateway.dto.ApiGatewayJobTypeDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobTypeDto;

import java.util.Set;

public interface JobTypeService {

    ApiGatewayNewJobTypeDto create(ApiGatewayNewJobTypeDto newJobTypeDto);

    void delete(Long id);

    Set<ApiGatewayJobTypeDto> findAll();

    ApiGatewayJobTypeDto update(ApiGatewayJobTypeDto jobTypeDto);

    ApiGatewayJobTypeDto findById(Long id);

    ApiGatewayJobTypeDto findByName(String name);
}
