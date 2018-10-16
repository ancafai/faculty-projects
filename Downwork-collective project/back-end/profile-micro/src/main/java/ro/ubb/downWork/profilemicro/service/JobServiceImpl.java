package ro.ubb.downWork.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.downWork.profilemicro.dto.JobDto;
import ro.ubb.downWork.profilemicro.dto.NewJobDto;
import ro.ubb.downWork.profilemicro.dto.filters.JobFilter;
import ro.ubb.downWork.profilemicro.mapper.JobMapper;
import ro.ubb.downWork.profilemicro.mapper.NewJobMapper;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.model.Notification;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.model.PersonJob;
import ro.ubb.downWork.profilemicro.repository.JobRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private NewJobMapper newJobMapper;

    @Override
    public NewJobDto create(NewJobDto newJobDto) {
        if (jobRepository.findByTitle(newJobDto.getTitle()) == null) {
            Job createdJob = newJobMapper.toInternal(newJobDto);
            return newJobMapper.toExternal(jobRepository.save(createdJob));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Job deletedJobType = jobRepository.findOne(id);
        jobRepository.delete(deletedJobType);
    }

    @Override
    public Set<JobDto> findAll() {
        Set<JobDto> jobTypes = jobMapper.toExternals(new HashSet<>((List<Job>) jobRepository.findAll()));
        return jobTypes;
    }

    @Override
    public JobDto findById(Long id) {
        return jobMapper.toExternal(jobRepository.findOne(id));
    }

    @Override
    public JobDto findByTitle(String name) {
        Job dto = jobRepository.findByTitle(name);
        return jobMapper.toExternal(dto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobDto> findAllPages(int page, int size, Specification<Job> jobFilter) {
        PageRequest pageRequest = (PageRequest) createPageRequest(page, size);
        Page<Job> resultPage = jobRepository.findAll(Specifications.where(jobFilter), pageRequest);
        return jobMapper.toExternalPage(resultPage);
    }

    @Override
    @Transactional
    public Job addCandidate(Long jobId, Long candidateId) {
        Person person = personRepository.findOne(candidateId);
        Job job = jobRepository.findOne(jobId);
        job.addCandidate(person);
        return job;
    }

    @Override
    @Transactional
    public Job hireCandidate(Long jobId, Long candidateId) {
        Person person = personRepository.findOne(candidateId);
        Job job = jobRepository.findOne(jobId);
        job.hireCandidate(person);
        return job;
    }

    @Override
    @Transactional
    public Job acceptJob(Long jobId, Long candidateId) {
        Person person = personRepository.findOne(candidateId);
        Job job = jobRepository.findOne(jobId);
        job.acceptJob(person);
        return job;
    }

    @Override
    public Set<JobDto> getToAcceptJobs(Long candidateId) {
        Person candidate = personRepository.findOne(candidateId);
        Set<JobDto> jobTypes = jobMapper.toExternals(new HashSet<>(candidate.getPersonAppliedJobs().stream()
                .filter(pj->!pj.isHasAccepted())
                .map(PersonJob::getJob).collect(Collectors.toList())));
        return jobTypes;
    }

    @Override
    public Set<JobDto> getCompletedJobs(Long candidateId) {
        Person candidate = personRepository.findOne(candidateId);
        Set<JobDto> jobTypes = jobMapper.toExternals(new HashSet<>(candidate.getPersonAppliedJobs().stream()
                .filter(pj->pj.isHasAccepted())
                .map(PersonJob::getJob)
                .filter(job->{
                    if(job.getEndDate()==null) {
                        return true;
                    }
                    Date crtDate = new Date(Calendar.getInstance().getTime().getTime());
                    if(crtDate.compareTo(job.getEndDate())>=0) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList())));
        return jobTypes;
    }

    @Override
    public Set<Job> getCompletedJobsNormal(Long candidateId) {
        Person candidate = personRepository.findOne(candidateId);
        return new HashSet<>(candidate.getPersonAppliedJobs().stream()
                .filter(pj->pj.isHasAccepted())
                .map(PersonJob::getJob)
                .filter(job->{
                    if(job.getEndDate()==null) {
                        return true;
                    }
                    Date crtDate = new Date(Calendar.getInstance().getTime().getTime());
                    if(crtDate.compareTo(job.getEndDate())>=0) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList()));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<JobDto> findBySearchTerm(String searchTerm) {
        PageRequest pageRequest = (PageRequest) createPageRequest(0, 20);
        Page<Job> searchResultPage = jobRepository.findByDescriptionContainsOrTitleContainsAllIgnoreCase(searchTerm,
                searchTerm, pageRequest);

        return jobMapper.toExternalPage(searchResultPage);
    }

    private Pageable createPageRequest(int page, int size) {
        return new PageRequest(page,
                size,
                new Sort(Sort.Direction.DESC, "description")
                        .and(new Sort(Sort.Direction.ASC, "title"))
        );
    }

    @Override
    @Transactional
    public JobDto update(JobDto jobDto) {
        Job jobToUpdate = jobRepository.findOne(jobDto.getId());
        // TODO make proper update with consideration of relationships
//        jobTypeToUpdate.setFirstname(personDto.getFirstname());
//        personToUpdate.setLastname(personDto.getLastname());
//        personToUpdate.setDescription(personDto.getDescription());
//        personToUpdate.setLocation(personDto.getLocation());
//        personToUpdate.setPicture(personDto.getPicture());

        Job jobUpdated = jobRepository.save(jobToUpdate);
        return jobMapper.toExternal(jobUpdated);
    }
}
