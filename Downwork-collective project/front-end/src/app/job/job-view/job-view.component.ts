import {Component, OnInit, Input} from "@angular/core";
import {GlobalApp} from "../../helpers/global";
import {Job} from "../shared/job.model";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {JobService} from "../shared/job.service";
import {JobCostType} from "../shared/job-cost-type.enum";
import {Person} from "../../profile/shared/person.model";
import {ProfileModalComponent} from "../../profile/profile-modal/profile-modal.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-job-view",
  templateUrl: "./job-view.component.html",
  styleUrls: ["./job-view.component.scss"]
})
export class JobViewComponent implements OnInit {

  job: Job = new Job(0, "", "", "", "", "", new Date(), "", new Date(), "", 0, JobCostType.SESSION, "", "");
  descrTruncated: string;
  viewDescription: string;

  app: GlobalApp;

  candidates: Person[][] = [];
  @Input() id: number;

  private url: String = "http://localhost:8080/api/job/";

  constructor(private route: ActivatedRoute,
              private jobService: JobService,
              private router: Router,
              private modalService: NgbModal) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.jobService.getJobById(params.id, "/getbyid/"))
      .subscribe((res: Job) => {
        this.job = res;
		if (this.job.description != null) {
		this.descrTruncated = this.job.description.slice(0, 170);
		}
		this.viewDescription = "...View More";
        if(res.owner === this.app.localStorageItem("username")) {
          this.jobService.getCandidates("/getcandidates/" + res.id).subscribe(
            (persons: Person[]) => {
              const candidatesFromBack = persons;
              let i = 0;
              for (let j = 0; j < Math.ceil(candidatesFromBack.length / 4); j++) {
                this.candidates.push([]);
                if (j === Math.ceil(candidatesFromBack.length / 4) - 1) {
                  for (let k = 0; k < candidatesFromBack.length % 4; k++) {
                    this.candidates[j][i % 4] = candidatesFromBack[i];
                    i++;
                  }
                } else {
                  for (let k = 0; k < 4; k++) {
                    this.candidates[j][i % 4] = candidatesFromBack[i];
                    i++;
                  }
                }
              }

            });
        }
        console.log(res);
		});
	
      
  }
  
  
  showMore(): void {
	console.log(this.job.description);
	if (this.viewDescription == "...View More") {
		this.descrTruncated = this.job.description;	
		this.viewDescription = "...View Less";
	}
	else if (this.viewDescription == "...View Less"){
		this.descrTruncated = this.job.description.slice(0, 170);
		this.viewDescription = "...View More";
	}
  }

  launchModal(content: Person, jobId: number): void{
    const modalRef = this.modalService.open(ProfileModalComponent);
    modalRef.componentInstance.person = content;
    modalRef.componentInstance.jobId = jobId;
  }

  apply(): void{
    this.jobService.applyToJob("addcandidate", this.job.id, +this.app.localStorageItem("personId")).subscribe(
      () => { alert("We'll notify you if you've been selected!"); }
    );
  }

}
