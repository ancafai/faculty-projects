package ro.ubb.downWork.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiGatewayNewJobTypeDto implements Serializable {
    private String name;

    @Override
    public String toString() {
        return "ApiGatewayNewJobTypeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

