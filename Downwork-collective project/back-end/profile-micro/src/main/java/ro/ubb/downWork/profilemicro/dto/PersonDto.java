package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.util.Arrays;

/**
 * Created by langchristian96 on 10/20/2017.
 */

public class PersonDto extends NewPersonDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String description;
    private byte[] picture;
    private JobDtoList jobs;
    private String mail;
    private String resettoken;

    private PersonDto() {
    }

    public PersonDto(Long id,
                     String username,
                     String password,
                     String location,
                     String firstname,
                     String lastname,
                     String description,
                     byte[] picture,
                     JobDtoList jobs) {
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

    public JobDtoList getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", description='" + description + '\'' +
                ", picture=" + Arrays.toString(picture) +
                ", jobs=" + jobs +
                '}';
    }
}

