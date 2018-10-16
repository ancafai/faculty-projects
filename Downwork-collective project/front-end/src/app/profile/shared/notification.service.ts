
import {Inject, Injectable, Optional} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";

import {Observable} from "rxjs";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";
import {Notificationn} from "./notification.model";


@Injectable()
export class NotificationnService {
  private personsUrl = "http://localhost:8080/api/person/getnotifications/";
  private headers = new Headers({"Content-Type": "application/json"});

  constructor(private http: Http) {
  }

  private heads2 = new Headers({"Access-Control-Allow-Origin": "http://localhost:8080"});

  getNotificationns(username: string): Observable<Notificationn[]> {
    console.log(this.personsUrl + username);
    return this.http.get(this.personsUrl + username, {withCredentials: true})
      .map(this.extractDataNotificationns)
      .catch(this.handleError);
  }


  private extractData(res: Response) {
    const body = res.json();
    return body.persons || {};
  }

  private extractDataNotificationns(res: Response) {
    const body = res.json();
    return body.notifications || {};
  }


  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || "";
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ""} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    return Observable.throw(errMsg);
  }

}

