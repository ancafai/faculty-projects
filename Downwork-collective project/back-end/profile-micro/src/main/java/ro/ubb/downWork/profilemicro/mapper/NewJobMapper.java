package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.NewJobDto;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.model.JobStatus;
import ro.ubb.downWork.profilemicro.repository.JobTypeRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class NewJobMapper extends AbstractMapper<Job, NewJobDto> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public Job toInternal(NewJobDto dto) {
        try {
            return Job.builder()
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .status(JobStatus.OPEN)
                    .location(dto.getLocation())
                    .occurrence(dto.getOccurrence())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .cost(dto.getCost())
                    .costType(dto.getCostType())
                    .owner(personRepository.findByUsername(dto.getOwner()))
                    .jobtype(jobTypeRepository.findByName(dto.getJobType()))
                    .isOffer(dto.getIsOffer())
                    .startTime(new Time(new SimpleDateFormat("HH:mm:ss").parse("00:00:00").getTime()))
                    .endTime(new Time(new SimpleDateFormat("HH:mm:ss").parse("23:59:59").getTime()))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NewJobDto toExternal(Job model) {
        return NewJobDto.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .location(model.getLocation())
                .occurrence(model.getOccurrence())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .cost(model.getCost())
                .costType(model.getCostType())
                .owner(model.getOwner().getUsername())
                .jobType(model.getJobtype().getName())
                .isOffer(model.getIsOffer())
                .build();
    }
}
