package ro.ubb.downWork.apigateway.dto;

import java.util.Arrays;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
public class ApiGatewayPersonDto extends ApiGatewayNewPersonDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String description;
    private byte[] picture;
    private ApiGatewayJobDtoList jobs;

    private ApiGatewayPersonDto() {
    }

    public ApiGatewayPersonDto(Long id,
                               String username,
                               String password,
                               String location,
                               String firstname,
                               String lastname,
                               String description,
                               byte[] picture,
                               ApiGatewayJobDtoList jobs) {
        super(username, password, location, "");
        this.picture = picture;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.id = id;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public ApiGatewayJobDtoList getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "ApiGatewayPersonDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", jobs=" + jobs +
                '}';
    }
}
