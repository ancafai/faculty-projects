package ro.ubb.downWork.profilemicro.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by langchristian96 on 1/11/2018.
 */
@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Notification extends BaseEntity<Long> {

    private String message;

    private boolean readd = false;

    @ManyToOne
    @JoinColumn(name = "ownernotif_id", nullable = false)
    private Person ownernotif;
}
