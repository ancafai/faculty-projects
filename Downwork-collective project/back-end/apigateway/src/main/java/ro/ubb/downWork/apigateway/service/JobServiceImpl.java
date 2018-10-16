package ro.ubb.downWork.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayPersonDtoList;
import ro.ubb.downWork.apigateway.mapper.NewJobMapper;
import ro.ubb.downWork.apigateway.mapper.JobMapper;
import ro.ubb.downWork.apigateway.mapper.PersonMapper;
import ro.ubb.downWork.profilemicro.dto.*;

import java.util.Map;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    private final String jobServiceUrl = "http://localhost:9998/profilemicro/job/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private NewJobMapper newJobMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public ApiGatewayNewJobDto create(ApiGatewayNewJobDto newPersonDto) {
        NewJobDto dto = restTemplate
                .postForEntity(jobServiceUrl + "create", newJobMapper.toInternal(newPersonDto), NewJobDto.class).getBody();
        return newJobMapper.toExternal(dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(this.jobServiceUrl + "delete/" + id);
    }

    @Override
    public Set<ApiGatewayJobDto> findAll() {
        JobDtoList jobDtoList = restTemplate.getForEntity(this.jobServiceUrl + "getall", JobDtoList.class).getBody();
        return jobMapper.toExternals(jobDtoList.getJobDtoList());
    }

    @Override
    public Page<ApiGatewayJobDto> findAllJobPage(int page, int size, String type, String location, String costType, String startTime, String endTime, String availableUntil, Boolean isOffer) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.jobServiceUrl + "pages/getall")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("is_offer", isOffer)
                .queryParam("type", type)
                .queryParam("location", location)
                .queryParam("cost_type", costType)
                .queryParam("start_time", startTime)
                .queryParam("end_time", endTime)
                .queryParam("available_until", availableUntil);
        ParameterizedTypeReference<RestResponsePage<JobDto>> responseType = new ParameterizedTypeReference<RestResponsePage<JobDto>>() {
        };

        ResponseEntity<RestResponsePage<JobDto>> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null/*httpEntity*/, responseType);
        return jobMapper.toExternalPage(result.getBody());
//        Set<JobDto> searchResult = new HashSet<>(result.getBody().getContent());
//        JobDtoList jobDtoList = restTemplate.getForEntity(builder.build().encode().toUri(), JobDtoList.class).getBody();
//        return new ApiGatewayJobDtoList(jobMapper.toExternals(searchResult));
    }

    @Override
    public Map<String, String> addCandidate(Long jobId, Long candidateId) {
        Map<String, String> res = (Map<String, String>) restTemplate.postForEntity(this.jobServiceUrl + "addcandidate/" + jobId + "/" + candidateId, "", Map.class).getBody();
        return res;
    }

    @Override
    public Map<String, String> hireCandidate(Long jobId, Long candidateId) {
        Map<String, String> res = (Map<String, String>) restTemplate.postForEntity(this.jobServiceUrl + "hirecandidate/" + jobId + "/" + candidateId, "", Map.class).getBody();
        return res;
    }

    @Override
    public Map<String, String> acceptJob(Long jobId, Long candidateId) {
        Map<String, String> res = (Map<String, String>)restTemplate.postForEntity(this.jobServiceUrl + "acceptjob/" + jobId + "/" + candidateId, "", Map.class).getBody();
        return res;
    }

    @Override
    public ApiGatewayPersonDtoList getCandidates(Long jobId) {
        PersonDtoList personDtoList = restTemplate.getForEntity(this.jobServiceUrl + "getcandidates/" + jobId, PersonDtoList.class).getBody();
        return new ApiGatewayPersonDtoList(personMapper.toExternals(personDtoList.getPersonDtoList()));
    }

    @Override
    public Set<ApiGatewayJobDto> getToAcceptJobs(Long candidateId) {
        JobDtoList jobDtoList = restTemplate.getForEntity(this.jobServiceUrl + "gettoacceptjobs/"+candidateId, JobDtoList.class).getBody();
        return jobMapper.toExternals(jobDtoList.getJobDtoList());
    }

    @Override
    public Set<ApiGatewayJobDto> getCompletedJobs(Long candidateId) {
        JobDtoList jobDtoList = restTemplate.getForEntity(this.jobServiceUrl + "getcompletedjobs/"+candidateId, JobDtoList.class).getBody();
        return jobMapper.toExternals(jobDtoList.getJobDtoList());
    }

    @Override
    public ApiGatewayJobDto update(ApiGatewayJobDto jobTypeDto) {
        HttpEntity<ApiGatewayJobDto> updatedInstance = new HttpEntity<>(jobTypeDto);
        ApiGatewayJobDto apiGatewayJobTypeDto = jobMapper.toExternal(
                restTemplate.exchange(this.jobServiceUrl + "update", HttpMethod.PUT, updatedInstance, JobDto.class).getBody());
        return apiGatewayJobTypeDto;
    }

    @Override
    public ApiGatewayJobDto findById(Long id) {
        JobDto dto = restTemplate.postForEntity(this.jobServiceUrl + "getbyid", id, JobDto.class).getBody();
        return jobMapper.toExternal(dto);
    }

    @Override
    public ApiGatewayJobDto findByTitle(String name) {
        JobDto dto = restTemplate.postForEntity(this.jobServiceUrl + "getbytitle", name, JobDto.class).getBody();
        return jobMapper.toExternal(dto);
    }
}
