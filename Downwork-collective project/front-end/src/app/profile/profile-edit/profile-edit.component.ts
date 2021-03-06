import {PersonService} from "../shared/person.service";

import {ActivatedRoute, Params, Router} from "@angular/router";
import {OnInit, Component, EventEmitter} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {Location} from "@angular/common";
import {Observable} from "rxjs/Observable";

import "rxjs/add/operator/switchMap";
import {Person} from "../shared/person.model";
import {GlobalApp} from "../../helpers/global";

@Component({
  selector: "app-profile-edit",
  templateUrl: "./profile-edit.component.html",
  styleUrls: ["./profile-edit.component.scss"]
})
export class ProfileEditComponent implements OnInit {

  // TODO use model class
  person = {
    "id": localStorage.getItem("personId"),
    "username": localStorage.getItem("username"),
    "password": "",
    "location": localStorage.getItem("location"),
    "firstname": localStorage.getItem("firstname"),
    "lastname": localStorage.getItem("lastname"),
    "description": localStorage.getItem("description"),
    "picture": localStorage.getItem("picture")
  };

  private url: String = "http://localhost:8080/api/person/";

  fileList: FileList;

  app: GlobalApp;

  // TODO use form to put data
  constructor(public formBuilder: FormBuilder,
              private router: Router,
              private personService: PersonService) {
    this.app = new GlobalApp();
  }

  ngOnInit() {
  }

  cancel(): void {
    this.router.navigate(["profile", this.person.username]);
  }
  
  deleteCancel(): void {
	this.router.navigate(["job", "home"]);
  }

  fileChange(e) {
    this.fileList = e.target.files;
  }

  editProfile() {
    if (this.fileList != null) {
      const file: File = this.fileList[0];
      const formData: FormData = new FormData();
      formData.append("file", file, file.name);
      formData.append("person", new Blob([JSON.stringify(this.person)], {
        type: "application/json"
      }));
      this.personService.updateWithFile(this.url + "/update/image", formData)
        .subscribe(
          data => {
            const person = new Person(data.json().username,
              "",
              data.json().location,
              data.json().id,
              data.json().firstname,
              data.json().lastname,
              data.json().description,
              data.json().picture
            );
            if (localStorage.getItem("personId") === person.id.toString()) {
              this.app.setLocalStorage(person);
            }
            this.cancel();
          },
          error => console.log(error)
        );
    }
    else {
      const formData: FormData = new FormData();
      formData.append("person", new Blob([JSON.stringify(this.person)], {
        type: "application/json"
      }));
      this.personService.updateWithFile(this.url + "/update/noimage", formData)
        .subscribe(
          data => {
            const person = new Person(data.json().username,
              "",
              data.json().location,
              data.json().id,
              data.json().firstname,
              data.json().lastname,
              data.json().description,
              null
            );
            if (localStorage.getItem("personId") === person.id.toString()) {
              this.app.setLocalStorage(person);
            }
            this.cancel();
          },
          error => console.log(error)
        );
    }
  }
  
  deleteProfile() {
	if(confirm("Are you sure you want to delete your profile?")) {
    this.personService.deletePerson("/person/delete", this.person).subscribe(data => {this.personService.getPersonsDelete(); this.deleteCancel();});
	}
  }
  
}
