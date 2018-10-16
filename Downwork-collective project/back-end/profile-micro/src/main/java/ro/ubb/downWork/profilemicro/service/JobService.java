package ro.ubb.downWork.profilemicro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.dto.NewJobDto;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.dto.filters.JobFilter;
import ro.ubb.downWork.profilemicro.model.Job;

import java.util.Set;

public interface JobService {

    NewJobDto create(NewJobDto newJobDto);

    void delete(Long id);

    Set<JobDto> findAll();

    JobDto update(JobDto jobDto);

    JobDto findById(Long id);

    JobDto findByTitle(String name);

    Page<JobDto> findBySearchTerm(String searchTerm);

    Page<JobDto> findAllPages(int size, int page, Specification<Job> jobFilter);

    Job addCandidate(Long jobId, Long candidateId);

    Job hireCandidate(Long jobId, Long candidateId);

    Job acceptJob(Long jobId, Long candidateId);

    Set<JobDto> getToAcceptJobs(Long candidateId);

    Set<JobDto> getCompletedJobs(Long candidateId);

    Set<Job> getCompletedJobsNormal(Long candidateId);
}
