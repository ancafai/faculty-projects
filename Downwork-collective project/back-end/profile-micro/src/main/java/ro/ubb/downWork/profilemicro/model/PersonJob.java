package ro.ubb.downWork.profilemicro.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by langchristian96 on 11/25/2017.
 */


@Entity
@Table(name="Person_Job")
@IdClass(PersonJobPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PersonJob implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name="Person")
    private Person person;


    @Id
    @ManyToOne(optional=false,fetch=FetchType.LAZY)
    @JoinColumn(name="Job")
    private Job job;

    private boolean isHired = false;

    private boolean hasAccepted = false;

}
