package ro.ubb.downWork.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobDto;
import ro.ubb.downWork.profilemicro.dto.NewJobDto;
import ro.ubb.downWork.profilemicro.mapper.AbstractMapper;

@Service
public class NewJobMapper extends AbstractMapper<NewJobDto, ApiGatewayNewJobDto> {

    @Override
    public NewJobDto toInternal(ApiGatewayNewJobDto dto) {
        return NewJobDto.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .occurrence(dto.getOccurrence())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .cost(dto.getCost())
                .costType(dto.getCostType())
                .owner(dto.getOwner())
                .jobType(dto.getJobType())
                .isOffer(dto.getIsOffer())
                .build();
    }

    @Override
    public ApiGatewayNewJobDto toExternal(NewJobDto model) {
        return ApiGatewayNewJobDto.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .location(model.getLocation())
                .occurrence(model.getOccurrence())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .cost(model.getCost())
                .costType(model.getCostType())
                .owner(model.getOwner())
                .jobType(model.getJobType())
                .isOffer(model.getIsOffer())
                .build();
    }
}
