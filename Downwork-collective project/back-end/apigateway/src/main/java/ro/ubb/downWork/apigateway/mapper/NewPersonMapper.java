package ro.ubb.downWork.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewPersonDto;
import ro.ubb.downWork.profilemicro.dto.NewPersonDto;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Service
public class NewPersonMapper extends AbstractMapper<NewPersonDto, ApiGatewayNewPersonDto> {

    @Override
    public NewPersonDto toInternal(ApiGatewayNewPersonDto dto) {
        NewPersonDto person = new NewPersonDto(dto.getUsername(), dto.getPassword(), dto.getLocation(), dto.getEmail());
        return person;
    }

    @Override
    public ApiGatewayNewPersonDto toExternal(NewPersonDto model) {
        ApiGatewayNewPersonDto newPersonDto = new ApiGatewayNewPersonDto(model.getUsername(), model.getPassword(), model.getLocation(), model.getEmail());
        return newPersonDto;
    }
}