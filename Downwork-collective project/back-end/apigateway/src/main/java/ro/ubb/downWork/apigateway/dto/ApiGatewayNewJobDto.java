package ro.ubb.downWork.apigateway.dto;

import lombok.*;
import ro.ubb.downWork.profilemicro.model.CostType;
import ro.ubb.downWork.profilemicro.model.JobOccurrence;

import java.io.Serializable;
import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayNewJobDto implements Serializable {
    private String title;
    private String description;
    private String location;
    private JobOccurrence occurrence;
    private Date startDate;
    private Date endDate;
    private Double cost;
    private CostType costType;
    private String owner;
    private String jobType;
    private Boolean isOffer;

    @Override
    public String toString() {
        return "ApiGatewayNewJobDto{" +
                "title='" + title + '\'' +
                "description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", occurrence=" + occurrence +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                ", costType=" + costType +
                ", owner='" + owner + '\'' +
                ", jobType='" + jobType + '\'' +
                ", isOffer=" + isOffer +
                '}';
    }
}
