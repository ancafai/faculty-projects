package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.repository.JobTypeRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;

@Service
public class JobMapper extends AbstractMapper<Job, JobDto> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public Job toInternal(JobDto dto) {
        Job job = Job.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .location(dto.getLocation())
                .occurrence(dto.getOccurrence())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .cost(dto.getCost())
                .costType(dto.getCostType())
                .owner(personRepository.findByUsername(dto.getOwner()))
                .jobtype(jobTypeRepository.findByName(dto.getJobType()))
                .isOffer(dto.getIsOffer())
                .build();
        job.setId(dto.getId());
        return job;
    }

    @Override
    public JobDto toExternal(Job model) {
        return new JobDto(model.getId(), model.getTitle(), model.getDescription(), model.getStatus(),
                model.getLocation(), model.getOccurrence(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(), model.getCostType(),
                model.getOwner().getUsername(), model.getJobtype().getName(),
                model.getIsOffer());
    }

    public Page<JobDto> toExternalPage(Page<Job> objectEntityPage) {
        return objectEntityPage.map(model -> new JobDto(model.getId(), model.getTitle(), model.getDescription(), model.getStatus(),
                model.getLocation(), model.getOccurrence(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(), model.getCostType(),
                model.getOwner().getUsername(), model.getJobtype().getName(), model.getIsOffer()));
    }
}
