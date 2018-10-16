package ro.ubb.downWork.profilemicro.dto;

public class JobTypeDto extends NewJobTypeDto {
    private Long id;
    private JobDtoList jobs;

    private JobTypeDto() {
    }

    public JobTypeDto(Long id,
                      String name,
                      JobDtoList jobs) {
        super(name);
        this.id = id;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public JobDtoList getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "JobTypeDto{" +
                "id=" + id +
                ", jobs=" + jobs +
                '}';
    }
}

