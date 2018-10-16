package ro.ubb.downWork.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.downWork.profilemicro.dto.*;
import ro.ubb.downWork.profilemicro.mapper.NewPersonMapper;
import ro.ubb.downWork.profilemicro.mapper.NotificationMapper;
import ro.ubb.downWork.profilemicro.mapper.PersonMapper;
import ro.ubb.downWork.profilemicro.mapper.ReviewMapper;
import ro.ubb.downWork.profilemicro.model.Notification;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.model.Review;
import ro.ubb.downWork.profilemicro.repository.NotificationRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;
import ro.ubb.downWork.profilemicro.repository.ReviewRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by langchristian96 on 10/20/2017.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private NewPersonMapper newPersonMapper;

    @Override
    public NewPersonDto create(NewPersonDto newPersonDto) {
        if (personRepository.findByUsername(newPersonDto.getUsername()) == null) {
            Person createdPerson = newPersonMapper.toInternal(newPersonDto);
            return newPersonMapper.toExternal(personRepository.save(createdPerson));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Person deletedPerson = personRepository.findOne(id);
        personRepository.delete(deletedPerson);
    }

    @Override
    public Set<PersonDto> findAll() {
        Set<PersonDto> persons = personMapper.toExternals(new HashSet<>((List<Person>) personRepository.findAll()));
        return persons;
    }

    @Override
    public PersonDto findById(Long id) {
        return personMapper.toExternal(personRepository.findOne(id));
    }

    @Override
    public PersonDto findByUsername(String username) {
        Person dto = personRepository.findByUsername(username);
        return personMapper.toExternal(dto);
    }

    @Override
    public Person findByUsernameNormal(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    public Person findByResetToken(String resettoken) {
        return personRepository.findByResettoken(resettoken);
    }

    @Override
    public Person findByMail(String mail) {
        return personRepository.findByMail(mail);
    }

    @Override
    public void updateToken(Person person) {
        personRepository.save(person);
    }

    @Override
//    @Transactional
    public NotificationDtoList getNotifications(String username) {
        Person pers = personRepository.findByUsername(username);
        Set<Notification> notifications = pers.getNotifications();
        NotificationDtoList res = new NotificationDtoList(notifications.stream().map(notif->
            notificationMapper.toExternal(notif)
        ).collect(Collectors.toSet()));
//        notifications.stream().forEach(notif->notif.setReadd(true));
        return res;
    }
    @Override
    @Transactional
    public NotificationDtoList updateNotifications(String username) {
        Person pers = personRepository.findByUsername(username);
        Set<Notification> notifications = pers.getNotifications();
        NotificationDtoList res = new NotificationDtoList(notifications.stream().map(notif->
                notificationMapper.toExternal(notif)
        ).collect(Collectors.toSet()));
        notifications.stream().forEach(notif->notif.setReadd(true));
        return res;
    }

    @Override
    @Transactional
    public Person addNotification(String username, Notification notification) {
        Person person = personRepository.findByUsername(username);
        notification.setOwnernotif(person);
        notificationRepository.save(notification);
        person.getNotifications().add(notification);
        return person;
    }

    @Override
    public ReviewDtoList getReviews(String username) {
        Person pers = personRepository.findByUsername(username);
        Set<Review> reviews = pers.getReviews();
        ReviewDtoList res = new ReviewDtoList(reviews.stream().map(review->
                reviewMapper.toExternal(review)
        ).collect(Collectors.toSet()));
        return res;
    }

    @Override
    @Transactional
    public Person addReview(String username, ReviewDto reviewDto) {
        Person person = personRepository.findByUsername(username);
        Review review = Review.builder().message(reviewDto.getMessage()).positive(reviewDto.isPositive()).reviewedPerson(person).reviewerUsername(reviewDto.getReviewer()).build();
        reviewRepository.save(review);
        person.getReviews().add(review);
        return person;
    }

    @Override
    @Transactional
    public PersonDto update(PersonDto personDto) {
        Person personToUpdate = personRepository.findOne(personDto.getId());
// TODO make proper update with consideration of relationships
        String firstName = personDto.getFirstname();
        String lastName = personDto.getLastname();
        String description = personDto.getDescription();
        String location = personDto.getLocation();
        byte[] pic = personDto.getPicture();
        if (firstName != EMPTY_STRING) {
            personToUpdate.setFirstname(personDto.getFirstname());
        }
        if (lastName != EMPTY_STRING) {
            personToUpdate.setLastname(personDto.getLastname());
        }
        if (description != EMPTY_STRING) {
            personToUpdate.setDescription(personDto.getDescription());
        }
        if (location != EMPTY_STRING) {
            personToUpdate.setLocation(personDto.getLocation());
        }
        if (pic != null) {
            personToUpdate.setPicture(personDto.getPicture());
        }
        Person personUpdated = personRepository.save(personToUpdate);
        return personMapper.toExternal(personUpdated);
    }
}
