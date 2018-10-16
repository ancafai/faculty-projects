package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.NewPersonDto;
import ro.ubb.downWork.profilemicro.model.Person;

import java.util.HashSet;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Service
public class NewPersonMapper extends AbstractMapper<Person, NewPersonDto> {

    @Override
    public Person toInternal(NewPersonDto dto) {
        Person person = Person.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .location(dto.getLocation())
                .mail(dto.getEmail())
                .jobsPosted(new HashSet<>())
                .build();
        return person;
    }

    @Override
    public NewPersonDto toExternal(Person model) {
        NewPersonDto newPersonDto = NewPersonDto.builder()
                .username(model.getUsername())
                .password(model.getPassword())
                .location(model.getLocation())
                .email(model.getMail())
                .build();
        return newPersonDto;
    }
}
