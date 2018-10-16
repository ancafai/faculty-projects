package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.dto.JobDtoList;
import ro.ubb.downWork.profilemicro.dto.JobTypeDto;
import ro.ubb.downWork.profilemicro.dto.JobTypeDtoList;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.model.JobType;
import ro.ubb.downWork.profilemicro.repository.JobRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class JobTypeMapper extends AbstractMapper<JobType, JobTypeDto> {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobType toInternal(JobTypeDto dto) {
        if(!dto.getJobs().getJobDtoList().isEmpty()) {
            JobType jobType = JobType.builder()
                    .name(dto.getName())
                    .jobs(dto.getJobs().getJobDtoList().stream()
                            .map(jobDto -> jobRepository.findOne(jobDto.getId()))
                            .collect(Collectors.toSet()))
                    .build();
            jobType.setId(dto.getId());
            return jobType;
        }else{
            JobType jobType = JobType.builder()
                    .name(dto.getName())
                    .jobs(new HashSet<>())
                    .build();
            jobType.setId(dto.getId());
            return jobType;
        }
    }

    @Override
    public JobTypeDto toExternal(JobType model) {
        if(!model.getJobs().isEmpty()) {
            JobTypeDto jobTypeDto = new JobTypeDto(model.getId(), model.getName(),
                    new JobDtoList(model.getJobs().stream()
                            .map(job -> new JobDto(job.getId(), job.getTitle(), job.getDescription(), job.getStatus(),
                                    job.getLocation(), job.getOccurrence(), job.getStartDate(), job.getEndDate(),
                                    job.getStartTime(), job.getEndTime(), job.getCost(), job.getCostType(),
                                    job.getOwner().getUsername(), job.getJobtype().getName(), job.getIsOffer()))
                            .collect(Collectors.toSet())));
            return jobTypeDto;
        } else {
            return new JobTypeDto(model.getId(), model.getName(),
                    new JobDtoList(new HashSet<>()));
        }
    }
}