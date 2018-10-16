package ro.ubb.downWork.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.downWork.profilemicro.model.Person;

/**
 * Created by langchristian96 on 10/20/2017.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findByUsername(String username);
    Person findByResettoken(String resettoken);
    Person findByMail(String mail);
}
