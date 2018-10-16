package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewPersonDto implements Serializable {
    private String username;
    private String password;
    private String location;
    private String email = "";

    @Override
    public String toString() {
        return "NewPersonDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

