package ro.ubb.downWork.apigateway.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDtoList;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDto;
import ro.ubb.downWork.profilemicro.dto.JobDtoList;
import ro.ubb.downWork.profilemicro.dto.PersonDto;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Service
public class PersonMapper extends AbstractMapper<PersonDto, ApiGatewayPersonDto> {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public PersonDto toInternal(ApiGatewayPersonDto dto) {
        if(!dto.getJobs().getApiGatewayJobDtoList().isEmpty()) {
            PersonDto personDto = new PersonDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getLocation(),
                    dto.getFirstname(),
                    dto.getLastname(),
                    dto.getDescription(),
                    dto.getPicture(),
                    new JobDtoList(dto.getJobs().getApiGatewayJobDtoList().stream()
                            .map(jobDto -> jobMapper.toInternal(jobDto))
                            .collect(Collectors.toSet())));
            return personDto;
        } else {
            PersonDto personDto = new PersonDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getLocation(),
                    dto.getFirstname(),
                    dto.getLastname(),
                    dto.getDescription(),
                    dto.getPicture(),
                    new JobDtoList(new HashSet<>()));
            return personDto;
        }
    }

    @Override
    public ApiGatewayPersonDto toExternal(PersonDto model) {
        ApiGatewayPersonDto personDto = new ApiGatewayPersonDto(model.getId(), model.getUsername(), model.getPassword(),
                model.getLocation(),
                model.getFirstname(),
                model.getLastname(),
                model.getDescription(),
                model.getPicture(),
                new ApiGatewayJobDtoList(model.getJobs().getJobDtoList().stream()
                        .map(job -> new ApiGatewayJobDto(job.getId(), job.getTitle(), job.getDescription(), job.getStatus(),
                                job.getLocation(), job.getOccurrence(), job.getStartDate(), job.getEndDate(),
                                job.getStartTime(), job.getEndTime(), job.getCost(), job.getCostType(),
                                job.getOwner(), job.getJobType(), job.getIsOffer()))
                        .collect(Collectors.toSet())));
        return personDto;
    }
}