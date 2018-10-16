package ro.ubb.downWork.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.downWork.profilemicro.model.JobType;

import java.util.LinkedHashSet;
import java.util.Set;

public interface JobTypeRepository extends CrudRepository<JobType, Long> {

    LinkedHashSet<JobType> findAllByOrderByNameAsc();

    JobType findByName(String name);

}
