import {JobCostType} from "./job-cost-type.enum";

export class Job {
  public id: number;
  public title: string;
  public description: string;
  public status: string;
  public location: string;
  public occurrence: string;
  public startDate: Date;
  public startTime: string;
  public endDate: Date;
  public endTime: string;
  public cost: number;
  public costType: JobCostType;
  public owner: string;
  public jobType: string;
  constructor(id: number,
              title: string,
              description: string,
              status: string,
              location: string,
              occurrence: string,
              startDate: Date,
              startTime: string,
              endDate: Date,
              endTime: string,
              cost: number,
              costType: JobCostType,
              owner: string,
              jobType: string) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.owner = owner;
    this.location = location;
    this.startDate = startDate;
    this.startTime = startTime;
    this.endDate = endDate;
    this.endTime = endTime;
    this.occurrence = occurrence;
    this.cost = cost;
    this.costType = costType;
    this.jobType = jobType;
  }
}
