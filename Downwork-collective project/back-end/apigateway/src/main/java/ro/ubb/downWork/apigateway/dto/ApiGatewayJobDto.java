package ro.ubb.downWork.apigateway.dto;

import ro.ubb.downWork.profilemicro.model.CostType;
import ro.ubb.downWork.profilemicro.model.JobOccurrence;
import ro.ubb.downWork.profilemicro.model.JobStatus;

import java.sql.Date;
import java.sql.Time;

public class ApiGatewayJobDto extends ApiGatewayNewJobDto {
    private Long id;
    private JobStatus status;
    private Time startTime;
    private Time endTime;

    private ApiGatewayJobDto() {
    }

    public ApiGatewayJobDto(Long id, String title, String description, JobStatus status, String location, JobOccurrence occurrence,
                            Date startDate, Date endDate,
                            Time startTime, Time endTime, Double cost,
                            CostType costType, String owner, String jobtype, Boolean isOffer) {
        super(title, description, location, occurrence, startDate, endDate, cost, costType, owner, jobtype, isOffer);
        this.id = id;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ApiGatewayJobDto{" +
                "id=" + id +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
