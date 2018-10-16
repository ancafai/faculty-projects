import {PersonService} from "../shared/person.service";
import {JobService} from "../../job/shared/job.service";
import {Job} from "../../job/shared/job.model";

import {ActivatedRoute, Params, Router} from "@angular/router";
import {OnInit, Component, EventEmitter} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {Location} from "@angular/common";
import {Observable} from "rxjs/Observable";

import "rxjs/add/operator/switchMap";
import {Person} from "../shared/person.model";
import {GlobalApp} from "../../helpers/global";

@Component({
  selector: "app-profile-view",
  templateUrl: "./profile-view.component.html",
  styleUrls: ["./profile-view.component.scss"]
})
export class ProfileViewComponent implements OnInit {


  person: Person = new Person();

  app: GlobalApp;

  jobs: Job[][] = [];

  photo: string;

  private url: String = "http://localhost:8080/api/person/";

  constructor(private route: ActivatedRoute,
			  private router: Router,
              private personService: PersonService,
			  private jobService: JobService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.personService.getByUsername(this.url + "getbyusername/", localStorage.getItem("username")))
      .subscribe(person => {
        if (this.app.localStorageItem("username") === person.username) {
          this.app.setLocalStorage(person);
        }
        this.person = person;
        this.photo = "data:image/jpg;base64," + person.picture;
      });

	this.postJobs();

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

  turnPage(page: number): void {
	console.log(page);
  }
}
