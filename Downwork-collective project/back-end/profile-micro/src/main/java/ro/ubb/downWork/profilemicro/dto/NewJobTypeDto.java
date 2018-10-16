package ro.ubb.downWork.profilemicro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewJobTypeDto implements Serializable {
    private String name;

    @Override
    public String toString() {
        return "NewJobTypeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

