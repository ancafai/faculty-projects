import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {

  private usersUrl = 'http://localhost:8080/api/person/reset/';
  private headers = new Headers({'Content-Type': 'application/json'});
  loggedIn = false;
  samePass = true;
  sentEmail = false;

  constructor(private http: Http) {
  }

  ngOnInit() {
  }

  savePass(newPass, retypeNewPass) {
    console.log(newPass);
    if (newPass != retypeNewPass) {
      this.samePass = false;
    }
  }

  sendPasswordMail(email) {
    console.log(email);
    let url = encodeURI(this.usersUrl+email+'/');
    this.http
      .post(url, "").subscribe();
    this.sentEmail = true;
  }
}
