package ro.ubb.downWork.profilemicro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by langchristian96 on 1/19/2018.
 */
@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review extends BaseEntity<Long> {

    private String message;

    private boolean positive;

    @ManyToOne
    @JoinColumn(name = "reviewedPerson_id", nullable = false)
    private Person reviewedPerson;

    private String reviewerUsername;


}
