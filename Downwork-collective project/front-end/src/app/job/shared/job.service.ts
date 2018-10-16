import { Injectable } from "@angular/core";
import { Http, Response, Headers, RequestOptions, URLSearchParams } from "@angular/http";

import {Observable} from "rxjs";
import "rxjs/add/operator/catch";
import "rxjs/add/operator/map";

import {Job} from "./job.model";
import {Page} from "./page.model";
import {Person} from "../../profile/shared/person.model";

@Injectable()
export class JobService {
  private jobsUrl = "http://localhost:8080/api/job";
  private headers = new Headers({"Content-Type": "application/json"});

  constructor(private http: Http) {
  }

  getJobs(urlNew: string,
          page: number,
          size: number,
          isOffer: boolean,
          type?: string,
          location?: string,
          costType?: string,
          startTime?: string,
          endTime?: string,
          availableUntil?: string): Observable<Page> {
    let params: URLSearchParams = new URLSearchParams();
    if(!page){
      page = 0;
    }
    if(!size){
      size = 2;
    }
    if(isOffer === null){
      isOffer = true;
    }
    params.set('page', page.toString());
    params.set('size', size.toString());
    params.set('is_offer', isOffer.toString());
    if(type){
      params.set('type', type);
    }
    if(location){
      params.set('location', location);
    }
    if(costType){
      params.set('cost_type', costType);
    }
    if(startTime){
      params.set('start_time', startTime);
    }
    if(endTime){
      params.set('end_time', endTime);
    }
    if(availableUntil){
      params.set('available_until', availableUntil);
    }
    let options: RequestOptions = new RequestOptions({headers: this.headers, params: params, withCredentials: true});
    return this.http.get(this.jobsUrl + urlNew, options)
      .map(this.extract)
      .catch(this.handleError);
  }

  getJobById(id: number, urlNew): Observable<Job> {
    let ur = this.jobsUrl + urlNew + id;
    return this.http.get(ur, {withCredentials: true})
      .map(this.extractDataJob)
      .catch(this.handleError);
  }

  public getCandidates(urlNew: string): Observable<Person[]> {
    return this.http.get(this.jobsUrl + urlNew, {withCredentials: true})
      .map(this.extractPersons)
      .catch(this.handleError);
  }

  public getToAcceptJobs(urlNew: string): Observable<Job[]> {
    return this.http.get(this.jobsUrl + urlNew, {withCredentials: true})
      .map(this.extractJobs)
      .catch(this.handleError);
  }

  public acceptJob(urlNew: string, jobId: number, candidateId: number): Observable<any> {
    return this.http
      .post(this.jobsUrl + "/" + urlNew + "/" + jobId + "/" + candidateId, {withCredentials: true, headers: this.headers})
      .map((res: Response) => {res.json();})
      .catch(this.handleError);
  }

  createJob(urlNew: string, job): Observable<Job> {
    let m = "";
    m = JSON.stringify(job);
    console.log("URL: ", urlNew);
    console.log("Json sent: ", m);
    return this.http
      .post(urlNew, m, {withCredentials: true, headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }


  updateJob(urlNew: string, job): Observable<Job> {
    const m = JSON.stringify(job);
    const url = `${this.jobsUrl + urlNew}/${job.id}`;
    return this.http
      .put(url, m, {withCredentials: true, headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateWithFile(url, formData: FormData): Observable<any> {
    const headers = new Headers();
    headers.append("Accept", "application/json");
    const options = new RequestOptions({
      withCredentials: true,
      headers: headers
    });
    return this.http.put(url, formData, options)
      .map(response => response)
      .catch(error => Observable.throw(error));
  }

  hire(url: string, jobId: number, candidateId: number): Observable<any> {
    return this.http
      .post(this.jobsUrl + "/" + url + "/" + jobId + "/" + candidateId, {withCredentials: true, headers: this.headers})
      .map((res: Response) => {res.json();})
      .catch(this.handleError);
  }

  applyToJob(url: string, jobId: number, candidateId: number): Observable<any>{
    return this.http
      .post(this.jobsUrl + "/" + url + "/" + jobId + "/" + candidateId, {withCredentials: true, headers: this.headers})
      .map((res: Response) => {res.json();})
      .catch(this.handleError);
  }

  private extractPersons(res: Response){
    const body = res.json().personDtoList || {};
    return body;
  }

  private extractJobs(res: Response){
    const body = res.json().apiGatewayJobDtoList || {};
    return body;
  }

  private extractId(res: Response) {
    const body = res.json();
    return body.jobId || {};
  }

  private extract(res: Response): Page {
    const body: Page = res.json();
    return body;
  }

  private extractClass(res: Response) {
    const body = res.json();
    return body.jobClass || {};
  }

  private extractData(res: Response) {
    const body = res.json();
    return body.content || {};
  }

  private extractDataJob(res: Response) {
    const body = res.json();
    return body || {};
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

