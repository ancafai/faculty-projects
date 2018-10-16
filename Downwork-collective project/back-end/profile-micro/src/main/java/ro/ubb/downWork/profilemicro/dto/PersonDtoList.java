package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PersonDtoList implements Serializable {
    private Set<PersonDto> personDtoList;

    @Override
    public String toString() {
        return "PersonDtoList{" +
                "personDtoList=" + personDtoList +
                '}';
    }
}
