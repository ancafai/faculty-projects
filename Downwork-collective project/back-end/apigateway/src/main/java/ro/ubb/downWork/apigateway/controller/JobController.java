package ro.ubb.downWork.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ro.ubb.downWork.apigateway.dto.*;
import ro.ubb.downWork.apigateway.service.JobService;

import java.util.Map;

@RequestMapping("/api/job/")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value = "getall", method = RequestMethod.GET, params = {"page", "size"})
    public Page<ApiGatewayJobDto> getAll(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("is_offer") Boolean isOffer,
                                         @RequestParam(value = "type", required = false) String type,
                                         @RequestParam(value = "location", required = false) String location,
                                         @RequestParam(value = "cost_type", required = false) String costType,
//            @RequestParam(value = "less_than30_hours", required = false) boolean lessThan30Hours,
                                         @RequestParam(value = "start_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String startTime,
                                         @RequestParam(value = "end_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String endTime,
                                         @RequestParam(value = "available_until", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String availableUntil) {
        return jobService.findAllJobPage(page, size, type, location, costType, startTime, endTime, availableUntil, isOffer);
    }

    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public ApiGatewayJobDto getById(@RequestBody Long id) {
        return jobService.findById(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ApiGatewayJobDto getByIdFromUrl(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ApiGatewayNewJobDto createJob(@RequestBody ApiGatewayNewJobDto newJobDto) {
        return jobService.create(newJobDto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ApiGatewayJobDto updateJob(@RequestBody ApiGatewayJobDto jobDto) {
        return jobService.update(jobDto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteJob(@PathVariable Long id) {
        jobService.delete(id);
    }

    @RequestMapping(value = "getbytitle", method = RequestMethod.POST)
    public ApiGatewayJobDto getJobByTitle(@RequestBody String title) {
        return jobService.findByTitle(title);
    }

    @RequestMapping(value = "getbytitle/{title}", method = RequestMethod.GET)
    public ApiGatewayJobDto getJobByTitleFromUrl(@PathVariable String title) {
        return jobService.findByTitle(title);
    }

    @RequestMapping (value = "addcandidate/{jobId}/{candidateId}", method = RequestMethod.POST)
    public Map<String, String> addCandidate(@PathVariable Long jobId, @PathVariable Long candidateId) {
        return jobService.addCandidate(jobId, candidateId);
    }

    @RequestMapping (value = "hirecandidate/{jobId}/{candidateId}", method = RequestMethod.POST)
    public Map<String, String> hireCandidate(@PathVariable Long jobId, @PathVariable Long candidateId) {
        return jobService.hireCandidate(jobId, candidateId);
    }

    @RequestMapping (value = "acceptjob/{jobId}/{candidateId}", method = RequestMethod.POST)
    public Map<String, String> acceptJob(@PathVariable Long jobId, @PathVariable Long candidateId) {
        return jobService.acceptJob(jobId, candidateId);
    }

    @RequestMapping (value = "getcandidates/{jobId}", method = RequestMethod.GET)
    public ApiGatewayPersonDtoList getCandidates(@PathVariable Long jobId){
        return jobService.getCandidates(jobId);
    }

    @RequestMapping (value = "gettoacceptjobs/{candidateId}", method = RequestMethod.GET)
    public ApiGatewayJobDtoList getToAcceptJobs(@PathVariable Long candidateId) {
        return new ApiGatewayJobDtoList(jobService.getToAcceptJobs(candidateId));
    }

    @RequestMapping (value = "getcompletedjobs/{candidateId}", method = RequestMethod.GET)
    public ApiGatewayJobDtoList getCompletedJobs(@PathVariable Long candidateId) {
        return new ApiGatewayJobDtoList(jobService.getCompletedJobs(candidateId));
    }

}
