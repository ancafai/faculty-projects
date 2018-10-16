package ro.ubb.downWork.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.downWork.profilemicro.model.Notification;

/**
 * Created by langchristian96 on 1/11/2018.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {
}
