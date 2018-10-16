import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions, URLSearchParams } from "@angular/http";

import {Observable} from "rxjs";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

import {JobType} from "./jobtype.model";

@Injectable()
export class JobTypeService {
  private jobTypesUrl = "http://localhost:8080/api/jobtype";
  private headers = new Headers({"Content-Type": "application/json"});

  constructor(private http: Http) {
  }

  getAll(): Observable<JobType[]>{
    let options: RequestOptions = new RequestOptions({headers: this.headers, withCredentials: true});
    return this.http.get(this.jobTypesUrl + "/getall", options)
      .map(this.extract)
      .catch(this.handleError);
  }

  private extract(res: Response): JobType[] {
    const body: JobType[] = [];
    let responseJson = res.json().apiGatewayJobTypeDtoList;
    for(let i = 0; i < responseJson.length; i++){
      let jobType = new JobType(responseJson[i].id, responseJson[i].name, responseJson[i].jobs.apiGatewayJobDtoList);
      body.push(jobType);
    }
    return body;
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
