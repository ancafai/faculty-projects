import { Component, OnInit } from "@angular/core";
import {GlobalApp} from "../../helpers/global";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {JobService} from "../shared/job.service";
import {NewJob} from "../shared/newjob.model";
import {JobTypeService} from "../shared/jobtype.service";
import {JobType} from "../shared/jobtype.model";
import {JobCostType} from "../shared/job-cost-type.enum";

@Component({
  selector: "app-job-add",
  templateUrl: "./job-add.component.html",
  styleUrls: ["./job-add.component.scss"]
})
export class JobAddComponent implements OnInit {

  private url: String = "http://localhost:8080/api/job/";

  cities = ["Aiud", "Alba Iulia", "Alexandria", "Anina", "Arad", "Azuga", "Bacău", "Baia Mare", "Baia Sprie", "Băicoi",
    "Băile Herculane", "Băile Olăneşti", "Bârlad", "Beiuş", "Bicaz", "Bistriţa", "Blaj", "Borşa", "Botoşani", "Braşov",
    "Brăila", "Bucureşti", "Buftea", "Buşteni", "Buzău", "Caracal", "Caransebeş", "Cavnic", "Câmpeni", "Câmpia Turzii",
    "Câmpulung Moldovenesc", "Cisnădie", "Cluj-Napoca", "Comăneşti", "Constanţa", "Covasna", "Craiova",
    "Curtea de Argeş", "Dej", "Deva", "Dorohoi", "Drăgăşani", "Drobeta-Turnu Severin", "Făgăraş", "Fălticeni", "Gherla",
    "Gura Humorului", "Horezu", "Huedin", "Hunedoara", "Iaşi", "Ineu", "Jibou", "Luduş", "Lugoj", "Mangalia", "Măcin",
    "Mărăşeşti", "Mediaş", "Miercurea Ciuc", "Mioveni", "Motru", "Nădlac", "Năsăud", "Năvodari", "Negru Vodă",
    "Ocna Mureş", "Ocna Sibiului", "Odorheiu Secuiesc", "Oradea", "Orăştie", "Otopeni", "Paşcani", "Petroşani",
    "Piatra Neamţ", "Piteşti", "Ploieşti", "Predeal", "Rădăuţi", "Râmnicu Sărat", "Râmnicu Vâlcea", "Râşnov",
    "Reghin", "Reşiţa", "Roman", "Rupea", "Satu Mare", "Săcele", "Sângeorz-Băi", "Sebeş", "Sfântu Gheorghe",
    "Sibiu", "Sighetu Marmaţiei", "Sighişoara", "Sinaia", "Slatina", "Slănic", "Sovata", "Suceava",
    "Sulina", "Şimleu Silvaniei", "Ştei"];
  selectedCity = "Location";

  occurrences = ["MONTHLY", "WEEKLY", "ONCE"];
  selectedOccurrence = "Occurence";
  selectedJobType: string = "Type";
  selectedJobCostType: string = "HOURLY";
  fileList: FileList;
  jobTypes: string[] = [];
  jobCostTypes: string[] = [];
  job: NewJob = new NewJob("","", "","", new Date(), new Date(),0,"HOURLY",localStorage.getItem("username"),"Type", false);

  app: GlobalApp;
  
  jobCostTypeSelected:boolean =  false;
  
  closed:boolean = false;

  constructor(private router: Router,
              private jobService: JobService,
              private jobTypeService: JobTypeService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
    const keys = Object.keys(JobCostType).filter(k => typeof JobCostType[k as any] === "string");
    this.jobCostTypes = keys.map(k => JobCostType[k as any]);
    this.jobTypeService.getAll().subscribe((jobTypes: JobType[]) => {
      this.jobTypes = jobTypes.map(jobType => jobType.name);
    });
  }

  cancel(): void {
    this.router.navigateByUrl("profile/" + localStorage.getItem("username"));
  }

  createJob() {
    this.jobService.createJob(this.url + "create", this.job)
      .subscribe(data => {
      this.router.navigate(["profile", localStorage.getItem("username")]);
    });
  }


  changeCity(newCity: string): void {
    this.selectedCity = newCity;
    this.job.location = newCity;
  }


  changeOccurrence(newOccurrence: string): void {
    this.selectedOccurrence = newOccurrence;
    this.job.occurrence = newOccurrence;
  }

  changeJobType(newJobType: string): void{
    this.selectedJobType = newJobType;
    this.job.jobType = newJobType;
  }

  changeJobCostType(newJobCostType: string): void{
    this.selectedJobCostType = newJobCostType;
    this.job.costType = newJobCostType;
  }
  
  changeCostType(costTypeJob: string) {
	this.job.costType = costTypeJob;
	console.log(this.job.costType);
	this.jobCostTypeSelected = true;
	this.closed = false;
  }
  

}
