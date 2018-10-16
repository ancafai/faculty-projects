package ro.ubb.downWork.apigateway.service;

import org.springframework.data.domain.Page;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDtoList;
import ro.ubb.downWork.profilemicro.dto.JobDtoList;

import java.util.Map;
import java.util.Set;

public interface JobService {

    ApiGatewayNewJobDto create(ApiGatewayNewJobDto apiGatewayNewJobDto);

    void delete(Long id);

    Set<ApiGatewayJobDto> findAll();

    ApiGatewayJobDto update(ApiGatewayJobDto apiGatewayJobDto);

    ApiGatewayJobDto findById(Long id);

    ApiGatewayJobDto findByTitle(String name);

    Page<ApiGatewayJobDto> findAllJobPage(int page, int size, String type, String location, String costType, String startTime, String endTime, String availableUntil, Boolean isOffer);

    Map<String, String> addCandidate(Long jobId, Long candidateId);

    Map<String, String> hireCandidate(Long jobId, Long candidateId);

    Map<String, String> acceptJob(Long jobId, Long candidateId);

    ApiGatewayPersonDtoList getCandidates(Long jobId);

    Set<ApiGatewayJobDto> getToAcceptJobs(Long candidateId);

    Set<ApiGatewayJobDto> getCompletedJobs(Long candidateId);
}
