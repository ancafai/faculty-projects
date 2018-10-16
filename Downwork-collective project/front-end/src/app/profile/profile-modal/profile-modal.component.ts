import {PersonService} from "../shared/person.service";
import {JobService} from "../../job/shared/job.service";
import {Job} from "../../job/shared/job.model";

import {ActivatedRoute, Params, Router} from "@angular/router";
import {OnInit, Component, EventEmitter, Input} from "@angular/core";

import "rxjs/add/operator/switchMap";
import {Person} from "../shared/person.model";
import {GlobalApp} from "../../helpers/global";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "profile-modal",
  templateUrl: "./profile-modal.component.html",
  styleUrls: ["./profile-modal.component.scss"]
})
export class ProfileModalComponent implements OnInit {
  @Input()
  person: Person;

  @Input()
  jobId: number;

  app: GlobalApp;

  jobs: Job[][] = [];

  private url: String = "http://localhost:8080/api/person/";

  constructor(private route: ActivatedRoute,
              private router: Router,
              private personService: PersonService,
              private jobService: JobService,
              public activeModal: NgbActiveModal) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.postJobs();
  }

  close(): void{
    this.activeModal.close();
  }

  postJobs() {
    // this.jobService.getJobs("/getall").subscribe(
    //   data => {
    // 	const jobsFromBack: Job[] = data;
    // 	var jobsPostedUser: Job[] = [];
    // 	for (let p = 0; p < jobsFromBack.length; p++){
    // 	if (jobsFromBack[p].owner === localStorage.getItem("username")){
    // 		jobsPostedUser.push(jobsFromBack[p]);
    // 	}
    // 	}
    // 	let i = 0;
    // 	for (let j = 0; j < Math.ceil(jobsPostedUser.length / 5); j++) {
    // 	  this.jobs.push([]);
    // 	  if (j === Math.ceil(jobsPostedUser.length / 5) - 1) {
    // 		if (jobsPostedUser.length % 5 === 0) {
    // 		  for (let k = 0; k < 5; k++) {
    // 			this.jobs[j][i % 5] = jobsPostedUser[i];
    // 			i++;
    // 		  }
    // 		} else {
    // 		  for (let k = 0; k < jobsPostedUser.length % 5; k++) {
    // 			this.jobs[j][i % 5] = jobsPostedUser[i];
    // 			i++;
    // 		  }
    // 		}
    // 	  } else {
    // 		for (let k = 0; k < 5; k++) {
    // 		  this.jobs[j][i % 5] = jobsPostedUser[i];
    // 		  i++;
    // 		}
    // 	  }
    // 	}
    // 	console.log(this.jobs);
    //
    //   });

  }

  seeDetails(id: string): void {
    this.router.navigateByUrl("job/view/" + id);
  }

  hire(): void {
    this.jobService.hire("hirecandidate", this.jobId, this.person.id).subscribe(
      () => {
        this.activeModal.close();
      }
    );
  }
}
