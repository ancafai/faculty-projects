package ro.ubb.downWork.profilemicro.dto;

import lombok.*;

import java.util.Set;

/**
 * Created by langchristian96 on 1/11/2018.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class NotificationDtoList {

    private Set<NotificationDto> notifications;

    @Override
    public String toString() {
        return "NotificationDtoList{" +
                "notificationDtoList=" + notifications +
                '}';
    }
}
