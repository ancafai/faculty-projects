package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class JobDtoList {
    @Override
    public String toString() {
        return "JobDtoList{" +
                "jobDtoList=" + jobDtoList +
                '}';
    }

    private Set<JobDto> jobDtoList;
}
