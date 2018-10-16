package ro.ubb.downWork.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.downWork.profilemicro.dto.JobTypeDto;
import ro.ubb.downWork.profilemicro.dto.NewJobTypeDto;
import ro.ubb.downWork.profilemicro.mapper.JobTypeMapper;
import ro.ubb.downWork.profilemicro.mapper.NewJobTypeMapper;
import ro.ubb.downWork.profilemicro.model.JobType;
import ro.ubb.downWork.profilemicro.repository.JobTypeRepository;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobTypeServiceImpl implements JobTypeService {

    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private JobTypeMapper jobTypeMapper;

    @Autowired
    private NewJobTypeMapper newJobTypeMapper;

    @Override
    public NewJobTypeDto create(NewJobTypeDto newJobTypeDto) {
        if (jobTypeRepository.findByName(newJobTypeDto.getName()) == null) {
            JobType createdJobType = newJobTypeMapper.toInternal(newJobTypeDto);
            return newJobTypeMapper.toExternal(jobTypeRepository.save(createdJobType));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        JobType deletedJobType = jobTypeRepository.findOne(id);
        jobTypeRepository.delete(deletedJobType);
    }

    @Override
    public Set<JobTypeDto> findAll() {
        Set<JobTypeDto> jobTypes = jobTypeMapper.toExternals(jobTypeRepository.findAllByOrderByNameAsc());
        return jobTypes;
    }

    @Override
    public JobTypeDto findById(Long id) {
        return jobTypeMapper.toExternal(jobTypeRepository.findOne(id));
    }

    @Override
    public JobTypeDto findByName(String name) {
        JobType dto = jobTypeRepository.findByName(name);
        return jobTypeMapper.toExternal(dto);
    }

    @Override
    @Transactional
    public JobTypeDto update(JobTypeDto jobTypeDto) {
        JobType jobTypeToUpdate = jobTypeRepository.findOne(jobTypeDto.getId());
// TODO make proper update with consideration of relationships
//        jobTypeToUpdate.setFirstname(personDto.getFirstname());
//        personToUpdate.setLastname(personDto.getLastname());
//        personToUpdate.setDescription(personDto.getDescription());
//        personToUpdate.setLocation(personDto.getLocation());
//        personToUpdate.setPicture(personDto.getPicture());

        JobType jobTypeUpdated = jobTypeRepository.save(jobTypeToUpdate);
        return jobTypeMapper.toExternal(jobTypeUpdated);
    }
}
