package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.dto.JobDtoList;
import ro.ubb.downWork.profilemicro.dto.PersonDto;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.repository.JobRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by CristianCosmin on 25.10.2017.
 */
@Service
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Person toInternal(PersonDto dto) {
        if(!dto.getJobs().getJobDtoList().isEmpty()) {
            Person person = Person.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .location(dto.getLocation())
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .description(dto.getDescription())
                    .picture(dto.getPicture())
                    .jobsPosted(dto.getJobs().getJobDtoList().stream()
                            .map(jobDto -> jobRepository.findOne(jobDto.getId()))
                            .collect(Collectors.toSet()))
                    .build();
            person.setId(dto.getId());
            return person;
        } else {
            Person person = Person.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .location(dto.getLocation())
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .description(dto.getDescription())
                    .picture(dto.getPicture())
                    .jobsPosted(new HashSet<>())
                    .build();
            person.setId(dto.getId());
            return person;
        }
    }

    @Override
    public PersonDto toExternal(Person model) {
        if(!model.getJobsPosted().isEmpty()) {
            PersonDto personDto = new PersonDto(model.getId(), model.getUsername(), model.getPassword(), model.getLocation(),
                    model.getFirstname(),
                    model.getLastname(),
                    model.getDescription(),
                    model.getPicture(),
                    new JobDtoList(model.getJobsPosted().stream()
                            .map(job -> new JobDto(job.getId(), job.getTitle(), job.getDescription(), job.getStatus(),
                                    job.getLocation(), job.getOccurrence(), job.getStartDate(), job.getEndDate(),
                                    job.getStartTime(), job.getEndTime(), job.getCost(), job.getCostType(),
                                    job.getOwner().getUsername(), job.getJobtype().getName(), job.getIsOffer()))
                            .collect(Collectors.toSet())));
            return personDto;
        } else {
            PersonDto personDto = new PersonDto(model.getId(), model.getUsername(), model.getPassword(), model.getLocation(),
                    model.getFirstname(),
                    model.getLastname(),
                    model.getDescription(),
                    model.getPicture(),
                    new JobDtoList(new HashSet<>()));
            return personDto;
        }
    }
}