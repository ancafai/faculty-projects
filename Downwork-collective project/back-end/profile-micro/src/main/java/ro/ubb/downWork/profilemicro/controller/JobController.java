package ro.ubb.downWork.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ro.ubb.downWork.profilemicro.dto.*;
import ro.ubb.downWork.profilemicro.dto.filters.JobFilter;
import ro.ubb.downWork.profilemicro.dto.filters.JobFilterBuilder;
import ro.ubb.downWork.profilemicro.dto.filters.SearchCriteria;
import ro.ubb.downWork.profilemicro.mapper.PersonMapper;
import ro.ubb.downWork.profilemicro.model.CostType;
import ro.ubb.downWork.profilemicro.model.Job;
import ro.ubb.downWork.profilemicro.model.Notification;
import ro.ubb.downWork.profilemicro.model.Person;
import ro.ubb.downWork.profilemicro.repository.JobRepository;
import ro.ubb.downWork.profilemicro.repository.PersonRepository;
import ro.ubb.downWork.profilemicro.service.JobService;
import ro.ubb.downWork.profilemicro.service.PersonService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequestMapping("/profilemicro/job/")
@RestController
public class JobController {

    private static final String HIRE_MESSAGE = "You've been hired! Job: ";
    private static final String ACCEPTANCE_MESSAGE = "The following user has accepted working for you: ";

    @Autowired
    private JobService jobService;

    @Autowired
    private PersonService personService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public JobDtoList getAll() {
        JobDtoList jobDtoList = new JobDtoList(jobService.findAll());
        return jobDtoList;
    }

    @RequestMapping (value = "gettoacceptjobs/{id}", method = RequestMethod.GET)
    public JobDtoList getToAcceptJobs(@PathVariable Long id) {
        JobDtoList jobDtoList = new JobDtoList(jobService.getToAcceptJobs(id));
        return jobDtoList;
    }

    @RequestMapping (value = "getcompletedjobs/{id}", method = RequestMethod.GET)
    public JobDtoList getCompletedJobs(@PathVariable Long id) {
        JobDtoList jobDtoList = new JobDtoList(jobService.getCompletedJobs(id));
        return jobDtoList;
    }

    @RequestMapping(value = "pages/getall", params = {"page", "size"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<JobDto> findPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "is_offer", required = false) String isOffer,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "cost_type", required = false) String costType,
//            @RequestParam(value = "less_than30_hours", required = false) boolean lessThan30Hours,
            @RequestParam(value = "start_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String startTime,
            @RequestParam(value = "end_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String endTime,
            @RequestParam(value = "available_until", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String availableUntil) {

        JobFilterBuilder builder = new JobFilterBuilder();

        if(isOffer != null && !isOffer.isEmpty()){
            JobFilter isOfferFilter = new JobFilter(new SearchCriteria("isOffer", ":", Boolean.valueOf(isOffer)));
            builder.with(isOfferFilter);
        }

        if (type != null && !type.isEmpty() && !type.equalsIgnoreCase("All")) {
            JobFilter typeFilter = new JobFilter(new SearchCriteria("jobtype", ":", type));
            builder.with(typeFilter);
        }

        if (location != null && !location.isEmpty()) {
            JobFilter locationFilter = new JobFilter(new SearchCriteria("location", ":", location));
            builder.with(locationFilter);
        }

        if (costType != null && !costType.isEmpty() && !costType.equalsIgnoreCase("All")) {
            JobFilter costTypeFilter =
                    new JobFilter(new SearchCriteria("costType", ":", CostType.valueOf(costType)));
            builder.with(costTypeFilter);
        }

        if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {

            builder.with(new JobFilter(new SearchCriteria("startTime", ">", startTime)));

            builder.with(new JobFilter(new SearchCriteria("endTime", "<", endTime)));
        }

        if (availableUntil != null && !availableUntil.isEmpty()) {
            JobFilter availableUntilFilter = new JobFilter(new SearchCriteria("endDate", "<", availableUntil));
            builder.with(availableUntilFilter);
        }
        Page<JobDto> resultPage = jobService.findAllPages(page, size, builder.build());
        return resultPage;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public JobDtoList findBySearchTerm(@RequestParam("searchTerm") String searchTerm) {
        return new JobDtoList(new HashSet<>(jobService.findBySearchTerm(searchTerm).getContent()));
    }

    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public JobDto getById(@RequestBody Long id) {
        return jobService.findById(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public JobDto getByIdFromUrl(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public NewJobDto createJob(@RequestBody NewJobDto newJobDto) {
        return jobService.create(newJobDto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public JobDto updateJob(@RequestBody JobDto jobDto) {
        return jobService.update(jobDto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteJob(@PathVariable Long id) {
        jobService.delete(id);
    }

    @RequestMapping(value = "getbytitle", method = RequestMethod.POST)
    public JobDto getJobByTitle(@RequestBody String title) {
        return jobService.findByTitle(title);
    }

    @RequestMapping(value = "getbytitle/{title}", method = RequestMethod.GET)
    public JobDto getJobByTitleFromUrl(@PathVariable String title) {
        return jobService.findByTitle(title);
    }

    @RequestMapping (value = "addcandidate/{jobId}/{candidateId}")
    public Map<String, String> addCandidate(@PathVariable Long jobId, @PathVariable Long candidateId) {
        jobService.addCandidate(jobId, candidateId);
        Map<String, String> result = new HashMap<>();
        result.put("result", "Candidate succesfully added to the job");
        return result;
    }

    @RequestMapping (value = "hirecandidate/{jobId}/{candidateId}")
    public Map<String, String> hireCandidate(@PathVariable Long jobId, @PathVariable Long candidateId) {
        jobService.hireCandidate(jobId, candidateId);
        Job job = jobRepository.getOne(jobId);
        Person pers = personRepository.findOne(candidateId);
        personService.addNotification(pers.getUsername(), new Notification(HIRE_MESSAGE+job.getTitle(),false,null));
        Map<String, String> result = new HashMap<>();
        result.put("result", "Candidate succesfully hired");
        return result;
    }


    @RequestMapping (value = "acceptjob/{jobId}/{candidateId}")
    public Map<String, String> acceptJob(@PathVariable Long jobId, @PathVariable Long candidateId) {
        jobService.acceptJob(jobId, candidateId);
        Job job = jobRepository.getOne(jobId);
        Person pers = personRepository.findOne(candidateId);
        personService.addNotification(job.getOwner().getUsername(), new Notification(ACCEPTANCE_MESSAGE+pers.getUsername(),false,null));
        Map<String, String> result = new HashMap<>();
        result.put("result", "Candidate succesfully accepted job");
        return result;
    }

    @RequestMapping (value = "getcandidates/{jobId}")
    public PersonDtoList getCandidates(@PathVariable Long jobId){
        Set<Person> candidates= jobRepository.findOne(jobId).getCandidates();
        return new PersonDtoList(this.personMapper.toExternals(candidates));
    }
}
