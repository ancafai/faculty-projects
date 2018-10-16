package ro.ubb.downWork.profilemicro.model;

import lombok.*;

import java.io.Serializable;

/**
 * Created by langchristian96 on 11/25/2017.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PersonJobPK implements Serializable {
    private Person person;
    private Job job;


}
