import { Component, OnInit } from '@angular/core';
import {GlobalApp} from "../../helpers/global";
import {Job} from "../../job/shared/job.model";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import { Location } from '@angular/common';
import {JobService} from "../../job/shared/job.service";

@Component({
  selector: 'app-jobs-pending',
  templateUrl: './jobs-pending.component.html',
  styleUrls: ['./jobs-pending.component.scss']
})
export class JobsPendingComponent implements OnInit {

  private url: String = "http://localhost:8080/api/person/";

  app: GlobalApp;

  jobs: Job[] = [];

  constructor(public formBuilder: FormBuilder,
              private router: Router,
              private location: Location,
              private jobService: JobService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.jobService.getToAcceptJobs("/gettoacceptjobs/"+ localStorage.getItem("personId")).subscribe(
      data => {
        const jobsFromBack: Job[] = data;
        for (let i = 0; i < jobsFromBack.length ; i++) {
          this.jobs.push(jobsFromBack[i]);
        }
        console.log(this.jobs);
      });
  }
  acceptJob(jobId: number) {
    let candidateId = localStorage.getItem('personId');
    this.jobService.acceptJob('acceptjob',jobId, parseInt(candidateId)).subscribe(() => {
      alert("Job succesfully accepted");
      this.router.navigateByUrl("/profile/"+localStorage.getItem("username"));

    });
  }


}
