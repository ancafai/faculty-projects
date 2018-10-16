package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class JobTypeDtoList implements Serializable {
    private Set<JobTypeDto> jobTypeDtoList;

    @Override
    public String toString() {
        return "JobTypeDtoList{" +
                "jobTypeDtoList=" + jobTypeDtoList +
                '}';
    }
}
