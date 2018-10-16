package ro.ubb.downWork.profilemicro.service;

import ro.ubb.downWork.profilemicro.dto.*;
import ro.ubb.downWork.profilemicro.model.Notification;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.model.Review;

import java.util.Set;

/**
 * Created by langchristian96 on 10/20/2017.
 */
public interface PersonService {

    NewPersonDto create(NewPersonDto newPersonDto);

    void delete(Long id);

    Set<PersonDto> findAll();

    PersonDto update(PersonDto personDto);

    PersonDto findById(Long id);

    PersonDto findByUsername(String username);

    Person findByUsernameNormal(String username);

    Person findByResetToken(String resettoken);

    Person findByMail(String mail);

    void updateToken(Person person);

    NotificationDtoList getNotifications(String username);

    Person addNotification(String username, Notification notification);

    ReviewDtoList getReviews(String username);

    Person addReview(String username, ReviewDto review);
    
    NotificationDtoList updateNotifications(String username);

}
