package ro.ubb.downWork.apigateway.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayJobTypeDtoList implements Serializable {
    private Set<ApiGatewayJobTypeDto> apiGatewayJobTypeDtoList;

    @Override
    public String toString() {
        return "ApiGatewayJobTypeDtoList{" +
                "apiGatewayJobTypeDtoList=" + apiGatewayJobTypeDtoList +
                '}';
    }
}
