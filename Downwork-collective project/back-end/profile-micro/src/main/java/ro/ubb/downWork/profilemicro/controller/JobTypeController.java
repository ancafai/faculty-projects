package ro.ubb.downWork.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.downWork.profilemicro.dto.*;
import ro.ubb.downWork.profilemicro.service.JobTypeService;

@RequestMapping("/profilemicro/jobtype/")
@RestController
public class JobTypeController {

    @Autowired
    private JobTypeService jobTypeService;

    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public JobTypeDtoList getAll() {
        JobTypeDtoList jobTypeDtoList = new JobTypeDtoList(jobTypeService.findAll());
        return jobTypeDtoList;
    }

    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public JobTypeDto getById(@RequestBody Long id) {
        return jobTypeService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public JobTypeDto getByIdFromUrl(@PathVariable Long id) {
        return jobTypeService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public NewJobTypeDto createPerson(@RequestBody NewJobTypeDto newJobTypeDto) {
        return jobTypeService.create(newJobTypeDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public JobTypeDto updatePerson(@RequestBody JobTypeDto jobTypeDto) {
        return jobTypeService.update(jobTypeDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        jobTypeService.delete(id);
    }

    @RequestMapping (value = "getbyname", method = RequestMethod.POST)
    public JobTypeDto getJobTypeByName(@RequestBody String name) {
        return jobTypeService.findByName(name);
    }

    @RequestMapping (value = "getbyname/{name}", method = RequestMethod.GET)
    public JobTypeDto getJobTypeByNameFromUrl(@PathVariable String name) {
        return jobTypeService.findByName(name);
    }
}
