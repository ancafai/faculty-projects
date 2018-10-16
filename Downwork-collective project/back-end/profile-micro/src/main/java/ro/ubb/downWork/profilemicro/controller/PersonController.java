package ro.ubb.downWork.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.ubb.downWork.profilemicro.dto.*;
import ro.ubb.downWork.profilemicro.mapper.NotificationMapper;
import ro.ubb.downWork.profilemicro.mapper.PersonMapper;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.service.EmailService;
import ro.ubb.downWork.profilemicro.service.JobService;
import ro.ubb.downWork.profilemicro.service.PersonService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Path;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by langchristian96 on 10/20/2017.
 */
@RequestMapping("/profilemicro/person/")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private JobService jobService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public PersonDtoList getAll() {
        PersonDtoList personDtoList = new PersonDtoList(personService.findAll());
        return personDtoList;
    }

    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public PersonDto getById(@RequestBody Long id) {
        return personService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public PersonDto getByIdFromUrl(@PathVariable Long id) {
        return personService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public NewPersonDto createPerson(@RequestBody NewPersonDto newPersonDto) {
        return personService.create(newPersonDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @RequestMapping (value = "getbyusername", method = RequestMethod.POST)
    public PersonDto getPersonByUsername(@RequestBody String username) {
        return personService.findByUsername(username);
    }

    @RequestMapping (value = "getbyusername/{username}", method = RequestMethod.GET)
    public PersonDto getPersonByUsernameFromUrl(@PathVariable String username) {
        return personService.findByUsername(username);
    }


    @RequestMapping (value = "getnotifbyusername/{username}", method = RequestMethod.GET)
    public NotificationDtoList getNotificationsByUsernameFromUrl(@PathVariable String username) {
        return personService.getNotifications(username);
    }

    @RequestMapping (value = "updatenotifbyusername/{username}", method = RequestMethod.PUT)
    public NotificationDtoList updateNotificationsByUsernameFromUrl(@PathVariable String username, @RequestBody String usernameBody) {
        return personService.updateNotifications(username);
    }


    @RequestMapping (value = "addreview/{username}", method = RequestMethod.POST)
    public void addReview(@PathVariable String username, @RequestBody ReviewDto reviewDto) {
        personService.addReview(username, reviewDto);
    }

    @RequestMapping (value = "getreviewsbyusername/{username}", method = RequestMethod.GET)
    public ReviewDtoList getReviewsByUsernameFromUrl(@PathVariable String username) {
        return personService.getReviews(username);
    }

    @RequestMapping (value = "reset/{mailEncoded}/", method = RequestMethod.POST)
    public void resetPassword(@PathVariable String mailEncoded) {
        String mail = "langchristian96@gmail.com";
        try {
            mail = java.net.URLDecoder.decode(mailEncoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Person person = personService.findByMail(mail);
        if(person != null) {
            person.setResettoken(UUID.randomUUID().toString());
            personService.updateToken(person);

            String appUrl = "http://localhost:4200";

//            // Email message
//            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//            passwordResetEmail.setFrom("downwork2019@outlook.com");
//            passwordResetEmail.setTo("mktbh.0624@yahoo.com");
//            passwordResetEmail.setSubject("Reset your Downwork password");
//            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
//                    + "/reset/" + person.getResettoken());
//
//            emailService.sendEmail(passwordResetEmail);

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("downwork2019@outlook.com", "worldwar2");
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("downwork2019@outlook.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(mail));
                message.setSubject("Reset your Downwork password");
                message.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset/" + person.getResettoken());

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @RequestMapping (value = "changepassword/{token}", method = RequestMethod.POST)
    public void changePassword(@PathVariable String token, @RequestParam String password) {
        Person person = personService.findByResetToken(token);
        person.setPassword(passwordEncoder.encode(password));
        personService.updateToken(person);
    }

    @RequestMapping (value = "collaborated/{reviewer}/{reviewed}", method = RequestMethod.GET)
    public Map<String, String> haveCollaborated(@PathVariable String reviewer, @PathVariable String reviewed) {
        Map<String, String> res = new HashMap<>();
        Person reviewerPerson = personService.findByUsernameNormal(reviewer);
        Person reviewedPerson = personService.findByUsernameNormal(reviewed);
        Set<Job> completedJobs = jobService.getCompletedJobsNormal(reviewedPerson.getId());
        res.put("value", "false");
        completedJobs.stream().forEach(j->{
            if(j.getOwner().getId().equals(reviewerPerson.getId())) {
                res.put("value","true");
            }
        });

        return res;
    }

}
