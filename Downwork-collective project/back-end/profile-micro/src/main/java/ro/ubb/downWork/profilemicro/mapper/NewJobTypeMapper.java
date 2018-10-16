package ro.ubb.downWork.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.ubb.downWork.profilemicro.dto.NewJobTypeDto;
import ro.ubb.downWork.profilemicro.model.JobType;

import java.util.HashSet;

@Service
public class NewJobTypeMapper extends AbstractMapper<JobType, NewJobTypeDto> {

    @Override
    public JobType toInternal(NewJobTypeDto dto) {
        return JobType.builder()
                .name(dto.getName())
                .jobs(new HashSet<>())
                .build();
    }

    @Override
    public NewJobTypeDto toExternal(JobType model) {
        return NewJobTypeDto.builder()
                .name(model.getName())
                .build();
    }
}
