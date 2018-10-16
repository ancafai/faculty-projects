package ro.ubb.downWork.profilemicro.dto.filters;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import ro.ubb.downWork.profilemicro.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobFilterBuilder {
    List<Specification<Job>> specs = new ArrayList<>();

    public JobFilterBuilder() {
        specs = new ArrayList<>();
    }

    public JobFilterBuilder with(JobFilter jobFilter) {
        specs.add(jobFilter);
        return this;
    }

    public Specification<Job> build() {
        if (specs.size() == 0) {
            return null;
        }

        Specification<Job> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
