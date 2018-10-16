package ro.ubb.downWork.apigateway.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDtoList;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobTypeDto;
import ro.ubb.downWork.profilemicro.dto.JobDtoList;
import ro.ubb.downWork.profilemicro.dto.JobTypeDto;
import ro.ubb.downWork.profilemicro.mapper.AbstractMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class JobTypeMapper extends AbstractMapper<JobTypeDto, ApiGatewayJobTypeDto> {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public JobTypeDto toInternal(ApiGatewayJobTypeDto dto) {
        if(!dto.getJobs().getApiGatewayJobDtoList().isEmpty()) {
            JobTypeDto jobType = new JobTypeDto(dto.getId(), dto.getName(),
                    new JobDtoList(dto.getJobs().getApiGatewayJobDtoList().stream()
                            .map(jobDto -> jobMapper.toInternal(jobDto))
                            .collect(Collectors.toSet())));
            return jobType;
        }else{
            JobTypeDto jobType = new JobTypeDto(dto.getId(), dto.getName(),
                    new JobDtoList(new HashSet<>()));
            return jobType;
        }
    }

    @Override
    public ApiGatewayJobTypeDto toExternal(JobTypeDto model) {
        if(!model.getJobs().getJobDtoList().isEmpty()) {
            ApiGatewayJobTypeDto apiGatewayJobType = new ApiGatewayJobTypeDto(model.getId(), model.getName(),
                    new ApiGatewayJobDtoList(model.getJobs().getJobDtoList().stream()
                            .map(jobDto -> jobMapper.toExternal(jobDto))
                            .collect(Collectors.toSet())));
            return apiGatewayJobType;
        }else{
            ApiGatewayJobTypeDto apiGatewayJobType = new ApiGatewayJobTypeDto(model.getId(), model.getName(),
                    new ApiGatewayJobDtoList(new HashSet<>()));
            return apiGatewayJobType;
        }
    }
}