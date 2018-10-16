package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.NotificationDto;
import ro.ubb.downWork.profilemicro.model.Notification;

/**
 * Created by langchristian96 on 1/11/2018.
 */
@Service
public class NotificationMapper extends AbstractMapper<Notification, NotificationDto>  {
    @Override
    public Notification toInternal(NotificationDto notificationDto) {
        return Notification.builder().message(notificationDto.getMessage()).readd(notificationDto.getRead()).build();
    }

    @Override
    public NotificationDto toExternal(Notification notification) {
        return new NotificationDto(notification.getMessage(),notification.isReadd());
    }
}
