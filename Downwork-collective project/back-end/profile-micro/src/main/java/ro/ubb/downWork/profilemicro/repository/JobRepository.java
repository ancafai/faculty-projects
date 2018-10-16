package ro.ubb.downWork.profilemicro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ro.ubb.downWork.profilemicro.dto.filters.JobFilter;
import ro.ubb.downWork.profilemicro.model.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    Job findByTitle(String title);

    Page<Job> findAll(Pageable pageRequest);

    Page<Job> findByDescriptionContainsOrTitleContainsAllIgnoreCase(String descriptionPart,
                                                                     String titlePart,
                                                                     Pageable pageReguest);

//    Page<Job> findAll(Pageable pageRequest, Specification<Job> jobFilters);
}
