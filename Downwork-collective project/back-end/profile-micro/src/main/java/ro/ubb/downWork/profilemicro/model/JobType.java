package ro.ubb.downWork.profilemicro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "job_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobType extends BaseEntity<Long>  {
    private String name;
    @OneToMany(mappedBy="jobtype")
    private Set<Job> jobs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobType jobType = (JobType) o;

        return name.equals(jobType.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
