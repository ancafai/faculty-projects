package ro.ubb.downWork.apigateway.dto;

public class ApiGatewayJobTypeDto extends ApiGatewayNewJobTypeDto {
    private Long id;
    private ApiGatewayJobDtoList jobs;

    private ApiGatewayJobTypeDto() {
    }

    public ApiGatewayJobTypeDto(Long id,
                                String name,
                                ApiGatewayJobDtoList jobs) {
        super(name);
        this.id = id;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public ApiGatewayJobDtoList getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "ApiGatewayJobTypeDto{" +
                "id=" + id +
                ", jobs=" + jobs +
                '}';
    }
}

