package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by langchristian96 on 1/19/2018.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ReviewDtoList {

    private Set<ReviewDto> reviews;

    @Override
    public String toString() {
        return "ReviewDtoList{" +
                "ReviewDtoList=" + reviews +
                '}';
    }
}