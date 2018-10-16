import {Component, Input, OnChanges, OnInit} from "@angular/core";
import {GlobalApp} from "../../helpers/global";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {JobService} from "../shared/job.service";
import {Job} from "../shared/job.model";
import {Page} from "../shared/page.model";
import {JobTypeService} from "../shared/jobtype.service";
import {JobType} from "../shared/jobtype.model";
import {JobCostType} from "../shared/job-cost-type.enum";

import {PersonService} from "../../profile/shared/person.service";

@Component({
  selector: "app-job-home",
  templateUrl: "./job-home.component.html",
  styleUrls: ["./job-home.component.scss"]
})
export class JobHomeComponent implements OnInit {

  app: GlobalApp;

  jobs: Job[][] = [];

  jobTypes: JobType[] = [];

  nrOfPages: number = 0;

  isFirstPage: boolean = true;

  isLastPage: boolean = true;

  pageNumber: number;

  pages: number[] = [];

  totalNumberOfJobs: number = 0;

  spinners: boolean = true;

  location: string;

  startTime: {hour: number, minute: number};

  endTime: {hour: number, minute: number};

  cities: string[] = ["All", "Aiud", "Alba Iulia", "Alexandria", "Anina", "Arad", "Azuga", "Bacău", "Baia Mare", "Baia Sprie", "Băicoi",
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

  jobCostTypes: JobCostType[] = [JobCostType.HOURLY, JobCostType.SESSION, JobCostType.VOLUNTEERING];

  minDate: Date = new Date(2017, 0, 1);

  constructor(private router: Router,
              private route: ActivatedRoute,
              private jobService: JobService,

              private jobTypeService: JobTypeService,
              private personService: PersonService) {
    this.app = new GlobalApp();
    this.location = this.app.localStorageItem("location");
  }

  ngOnInit() {
    this.type = "All";
    this.cost_type = "All";
    if (!this.app.localStorageItem("personId")) {
      this.personService.getByUsername("http://localhost:8080/api/person/getbyusername/", this.app.localStorageItem("username")).subscribe(person => {
        if (this.app.localStorageItem("username") === person.username) {
          this.app.setLocalStorage(person);
        }
      });
    }
    this.route.queryParams
      .subscribe((params: Params) => {
        this.totalNumberOfJobs = 0;
        let type: string = null;
        if (params.hasOwnProperty('type')) {
          type = params['type'];
        }
        let location: string = null;
        if (params.hasOwnProperty('location')) {
          location = params['location'];
        }else if(this.location === this.app.localStorageItem("location")){
          location = this.app.localStorageItem("location");
        }
        let cost_type: string = null;
        if (params.hasOwnProperty('cost_type')) {
          cost_type = params['cost_type'];
        }
        let start_time: string = null;
        if (params.hasOwnProperty('start_time')) {
          start_time = params['start_time'];
        }
        let end_time: string = null;
        if (params.hasOwnProperty('end_time')) {
          end_time = params['end_time'];
        }
        let available_until: string = null;
        if (params.hasOwnProperty('available_until')) {
          available_until = params['available_until'];
        }
        this.jobService.getJobs("/getall",
          params['page'], params['size'], params['isOffer'], type, location,
          cost_type, start_time, end_time, available_until).subscribe(
          (data: Page) => {
            this.pages = [];
            this.jobs = data.content;
            // this.size = data.size;
            this.nrOfPages = data.totalPages;
            this.isFirstPage = data.first;
            this.isLastPage = data.last;
            this.pageNumber = data.number;
            for (let i = 1; i <= this.nrOfPages; i++) {
              this.pages.push(i);
            }
          });
        this.jobTypeService.getAll().subscribe(
          (data: JobType[]) => {
            this.jobTypes = data;
            this.jobTypes.forEach(jobType => {
              this.totalNumberOfJobs += jobType.jobs.length;
            })
          }
        );
      });
  }

  @Input()
  set size(newSize: number){
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          page: 0,
          size: newSize
        },
        queryParamsHandling: 'merge'
      });
  }

  @Input()
  set type(newType: string){
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          page: 0,
          type: newType
        },
        queryParamsHandling: 'merge'
      });
  }

  changeCity(newLocation: string){
      if (newLocation === "All") {
        newLocation = ""
      }

      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          page: 0,
          location: newLocation
        },
        queryParamsHandling: 'merge'
      });
  }

  @Input()
  set cost_type(newCostType: string){
      this.router.navigate([], {
        relativeTo: this.route,
        queryParams: {
          page: 0,
          cost_type: newCostType,
        },
        queryParamsHandling: 'merge'
      });
  }

  filterTime() {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        start_time: this.getTimeString(this.startTime),
        end_time: this.getTimeString(this.endTime)
      },
      queryParamsHandling: 'merge'
    });
  }

  @Input()
  set available_until(newAvailableDate: string){
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: 0,
        available_until: this.getDateString(newAvailableDate)
      },
      queryParamsHandling: 'merge'
    });
  }

  seeDetails(id: string): void {
    this.router.navigateByUrl("job/view/" + id);
  }

  goToPage(page: number): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: page - 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  goToPreviousPage(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: this.pageNumber - 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  goToNextPage(): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: this.pageNumber + 1,
      },
      queryParamsHandling: 'merge'
    });
  }

  private getDateString(date: string): string {
    if (date) {
      return new Date(date).getFullYear()
        + "-" + (new Date(date).getMonth() + 1) + "-" +
        new Date(date).getDate();
    }
    return "";
  }

  private getTimeString(time: { hour: number, minute: number }): string {
    if (time) {
      return time.hour + ":" + time.minute + ":00";
    }
    return "";
  }
}

