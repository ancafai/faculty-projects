package ro.ubb.downWork.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobTypeDto;
import ro.ubb.downWork.apigateway.dto.ApiGatewayJobTypeDtoList;
import ro.ubb.downWork.apigateway.dto.ApiGatewayNewJobTypeDto;
import ro.ubb.downWork.apigateway.service.JobTypeService;

@RequestMapping("/api/jobtype/")
@RestController
public class JobTypeController {

    @Autowired
    private JobTypeService jobTypeService;

    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public ApiGatewayJobTypeDtoList getAll() {
        ApiGatewayJobTypeDtoList jobTypeDtoList = new ApiGatewayJobTypeDtoList(jobTypeService.findAll());
        return jobTypeDtoList;
    }

    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public ApiGatewayJobTypeDto getById(@RequestBody Long id) {
        return jobTypeService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public ApiGatewayJobTypeDto getByIdFromUrl(@PathVariable Long id) {
        return jobTypeService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public ApiGatewayNewJobTypeDto createPerson(@RequestBody ApiGatewayNewJobTypeDto newJobTypeDto) {
        return jobTypeService.create(newJobTypeDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public ApiGatewayJobTypeDto updatePerson(@RequestBody ApiGatewayJobTypeDto jobTypeDto) {
        return jobTypeService.update(jobTypeDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        jobTypeService.delete(id);
    }

    @RequestMapping (value = "getbyname", method = RequestMethod.POST)
    public ApiGatewayJobTypeDto getJobTypeByName(@RequestBody String name) {
        return jobTypeService.findByName(name);
    }

    @RequestMapping (value = "getbyname/{name}", method = RequestMethod.GET)
    public ApiGatewayJobTypeDto getJobTypeByNameFromUrl(@PathVariable String name) {
        return jobTypeService.findByName(name);
    }
}
