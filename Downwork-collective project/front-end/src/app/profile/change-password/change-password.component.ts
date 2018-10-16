import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {

  private usersUrl = 'http://localhost:8080/api/person/changepassword/';
  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http,
              private router: Router) {
  }

  ngOnInit() {
  }


  changePassword(newPassword) {
    console.log(newPassword);
    let args = this.router.url.split("/");
    let token = args[args.length - 1];
    let url = this.usersUrl + token;
    url = url + "?password=";
    url = url + newPassword;
    this.http
      .post(url, "").subscribe();
    this.router.navigateByUrl("");
  }
}
