package ro.ubb.downWork.profilemicro.service;

import ro.ubb.downWork.profilemicro.dto.JobTypeDto;
import ro.ubb.downWork.profilemicro.dto.NewJobTypeDto;

import java.util.List;
import java.util.Set;

public interface JobTypeService {

    NewJobTypeDto create(NewJobTypeDto newJobTypeDto);

    void delete(Long id);

    Set<JobTypeDto> findAll();

    JobTypeDto update(JobTypeDto jobTypeDto);

    JobTypeDto findById(Long id);

    JobTypeDto findByName(String name);
}
