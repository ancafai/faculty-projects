package ro.ubb.downWork.apigateway.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayPersonDtoList implements Serializable {
    private Set<ApiGatewayPersonDto> personDtoList;

    @Override
    public String toString() {
        return "PersonDtoList{" +
                "personDtoList=" + personDtoList +
                '}';
    }
}
