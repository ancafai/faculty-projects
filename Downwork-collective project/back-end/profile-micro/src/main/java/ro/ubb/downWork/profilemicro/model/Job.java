package ro.ubb.downWork.profilemicro.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Job extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated (EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JobStatus status;

    @Column(name = "location", nullable = false)
    private String location;

    @Enumerated (EnumType.STRING)
    @Column(name = "occurrence", nullable = false)
    private JobOccurrence occurrence;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name = "starttime")
    private Time startTime;

    @Column(name = "endtime")
    private Time endTime;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Enumerated (EnumType.STRING)
    @Column(name = "cost_type", nullable = false)
    private CostType costType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "job_type_id", nullable = false)
    private JobType jobtype;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PersonJob> candidates=new HashSet<>();

    @Column(name = "isoffer", nullable = false)
    private Boolean isOffer;

    public void addCandidate(Person person) {
        PersonJob personJob = new PersonJob();
        personJob.setJob(this);
        personJob.setPerson(person);
        candidates.add(personJob);
    }

    public Set<Person> getCandidates() {
        return Collections.unmodifiableSet(candidates
                                                .stream()
                                                .map(pj->pj.getPerson())
                                                .collect(Collectors.toSet()));
    }

    public void hireCandidate(Person person) {
        candidates.stream().filter(p->p.getPerson().getId().equals(person.getId())).forEach(p->p.setHired(true));
    }

    public void acceptJob(Person person) {
        candidates.stream().filter(p->p.getPerson().getId().equals(person.getId())).forEach(p->p.setHasAccepted(true));
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Job job = (Job) o;
//
//        if (!title.equals(job.title)) return false;
//        if (description != null ? !description.equals(job.description) : job.description != null) return false;
//        if (status != job.status) return false;
//        if (!location.equals(job.location)) return false;
//        if (occurrence != job.occurrence) return false;
//        if (!startDate.equals(job.startDate)) return false;
//        if (!endDate.equals(job.endDate)) return false;
//        if (startTime != null ? !startTime.equals(job.startTime) : job.startTime != null) return false;
//        if (endTime != null ? !endTime.equals(job.endTime) : job.endTime != null) return false;
//        if (!cost.equals(job.cost)) return false;
//        return costType == job.costType;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = title.hashCode();
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        result = 31 * result + status.hashCode();
//        result = 31 * result + location.hashCode();
//        result = 31 * result + occurrence.hashCode();
//        result = 31 * result + startDate.hashCode();
//        result = 31 * result + endDate.hashCode();
//        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
//        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
//        result = 31 * result + cost.hashCode();
//        result = 31 * result + costType.hashCode();
//        return result;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return isOffer == job.isOffer &&
                Objects.equals(title, job.title) &&
                Objects.equals(description, job.description) &&
                status == job.status &&
                Objects.equals(location, job.location) &&
                occurrence == job.occurrence &&
                Objects.equals(startDate, job.startDate) &&
                Objects.equals(endDate, job.endDate) &&
                Objects.equals(startTime, job.startTime) &&
                Objects.equals(endTime, job.endTime) &&
                Objects.equals(cost, job.cost) &&
                costType == job.costType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status, location, occurrence, startDate, endDate, startTime, endTime, cost, costType, isOffer);
    }
}
