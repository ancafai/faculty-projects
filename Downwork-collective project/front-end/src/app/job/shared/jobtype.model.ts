export class JobType{
  public id: number;
  public name: string;
  public jobs: string[];

  constructor(id: number, name: string, jobs?: string[]){
    this.id = id;
    this.name = name;
    this.jobs = jobs;
  }
}
