package ro.ubb.downWork.apigateway.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDto;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.dto.RestResponsePage;
import ro.ubb.downWork.profilemicro.mapper.AbstractMapper;

@Service
public class JobMapper extends AbstractMapper<JobDto, ApiGatewayJobDto> {

    @Override
    public JobDto toInternal(ApiGatewayJobDto dto) {
        return new JobDto(dto.getId(), dto.getTitle(), dto.getDescription(), dto.getStatus(),
                dto.getLocation(), dto.getOccurrence(), dto.getStartDate(), dto.getEndDate(),
                dto.getStartTime(), dto.getEndTime(), dto.getCost(), dto.getCostType(),
                dto.getOwner(), dto.getJobType(), dto.getIsOffer());
    }

    @Override
    public ApiGatewayJobDto toExternal(JobDto model) {
        return new ApiGatewayJobDto(model.getId(), model.getTitle(), model.getDescription(), model.getStatus(),
                model.getLocation(), model.getOccurrence(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(), model.getCostType(),
                model.getOwner(), model.getJobType(), model.getIsOffer());
    }

    public Page<ApiGatewayJobDto> toExternalPage(RestResponsePage<JobDto> objectEntityPage) {
        return objectEntityPage.map(model -> new ApiGatewayJobDto(model.getId(), model.getTitle(), model.getDescription(), model.getStatus(),
                model.getLocation(), model.getOccurrence(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(), model.getCostType(),
                model.getOwner(), model.getJobType(), model.getIsOffer()));
    }
}
