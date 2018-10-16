package ro.ubb.downWork.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobTypeDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobTypeDto;
import ro.ubb.downWork.apigateway.mapper.JobTypeMapper;
import ro.ubb.downWork.apigateway.mapper.NewJobTypeMapper;
import ro.ubb.downWork.profilemicro.dto.JobTypeDto;
import ro.ubb.downWork.profilemicro.dto.JobTypeDtoList;
import ro.ubb.downWork.profilemicro.dto.NewJobTypeDto;

import java.util.*;

@Service
public class JobTypeServiceImpl implements JobTypeService {

    private final String jobTypeServiceUrl = "http://localhost:9998/profilemicro/jobtype/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JobTypeMapper jobTypeMapper;

    @Autowired
    private NewJobTypeMapper newJobTypeMapper;

    @Override
    public ApiGatewayNewJobTypeDto create(ApiGatewayNewJobTypeDto newPersonDto) {
        NewJobTypeDto dto = restTemplate
                .postForEntity(jobTypeServiceUrl + "create", newJobTypeMapper.toInternal(newPersonDto), NewJobTypeDto.class).getBody();
        return newJobTypeMapper.toExternal(dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(this.jobTypeServiceUrl + "delete/" + id);
    }

    @Override
    public Set<ApiGatewayJobTypeDto> findAll() {
        JobTypeDtoList personDtoList = restTemplate.getForEntity(this.jobTypeServiceUrl + "getall", JobTypeDtoList.class).getBody();
        List<JobTypeDto> jobTypeDtoTempList = new ArrayList<>();
        jobTypeDtoTempList.addAll(personDtoList.getJobTypeDtoList());
        Collections.sort(jobTypeDtoTempList, new Comparator<JobTypeDto>() {
            @Override public int compare(JobTypeDto p1, JobTypeDto p2) {
                return p1.getName().compareToIgnoreCase(p2.getName()); // Ascending
            }

        });
        Set<JobTypeDto> jobTypeDtos = new LinkedHashSet<>();
        for(JobTypeDto jobType: jobTypeDtoTempList){
            jobTypeDtos.add(jobType);
        }
        return jobTypeMapper.toExternals(jobTypeDtos);
    }

    @Override
    public ApiGatewayJobTypeDto update(ApiGatewayJobTypeDto jobTypeDto) {
        HttpEntity<ApiGatewayJobTypeDto> updatedInstance = new HttpEntity<>(jobTypeDto);
        ApiGatewayJobTypeDto apiGatewayJobTypeDto = jobTypeMapper.toExternal(
                restTemplate.exchange(this.jobTypeServiceUrl + "update", HttpMethod.PUT, updatedInstance, JobTypeDto.class).getBody());
        return apiGatewayJobTypeDto;
    }

    @Override
    public ApiGatewayJobTypeDto findById(Long id) {
        JobTypeDto dto = restTemplate.postForEntity(this.jobTypeServiceUrl + "getbyid", id, JobTypeDto.class).getBody();
        return jobTypeMapper.toExternal(dto);
    }

    @Override
    public ApiGatewayJobTypeDto findByName(String name) {
        JobTypeDto dto = restTemplate.postForEntity(this.jobTypeServiceUrl + "getbyname", name, JobTypeDto.class).getBody();
        return jobTypeMapper.toExternal(dto);
    }
}
