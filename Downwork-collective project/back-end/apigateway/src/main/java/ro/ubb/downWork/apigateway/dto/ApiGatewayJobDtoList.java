package ro.ubb.downWork.apigateway.dto;

import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayJobDtoList {
    @Override
    public String toString() {
        return "ApiGatewayJobDtoList{" +
                "apiGatewayJobDtoList=" + apiGatewayJobDtoList +
                '}';
    }

    private Set<ApiGatewayJobDto> apiGatewayJobDtoList;
}
