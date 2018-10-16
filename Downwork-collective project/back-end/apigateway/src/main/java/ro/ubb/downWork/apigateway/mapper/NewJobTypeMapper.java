package ro.ubb.downWork.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobTypeDto;
import ro.ubb.downWork.profilemicro.dto.NewJobTypeDto;
import ro.ubb.downWork.profilemicro.mapper.AbstractMapper;
import ro.ubb.downWork.profilemicro.model.JobType;

import java.util.HashSet;

@Service
public class NewJobTypeMapper extends AbstractMapper<NewJobTypeDto, ApiGatewayNewJobTypeDto> {

    @Override
    public NewJobTypeDto toInternal(ApiGatewayNewJobTypeDto dto) {
        return NewJobTypeDto.builder()
                .name(dto.getName())
                .build();
    }

    @Override
    public ApiGatewayNewJobTypeDto toExternal(NewJobTypeDto model) {
        return ApiGatewayNewJobTypeDto.builder()
                .name(model.getName())
                .build();
    }
}
