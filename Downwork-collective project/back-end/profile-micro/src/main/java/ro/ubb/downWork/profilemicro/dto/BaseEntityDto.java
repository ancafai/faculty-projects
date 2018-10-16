package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by langchristian96 on 10/20/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntityDto implements Serializable {
    private Long id;
}
