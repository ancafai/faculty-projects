export class NewJob {
  public title: string;
  public description: string;
  public location: string;
  public occurrence: string;
  public startDate: Date;
  public endDate: Date;
  public cost: number;
  public costType: string;
  public owner: string;
  public jobType: string;
  public isOffer: boolean;
  constructor(
              title: string,
			  description: string,
              location: string,
              occurrence: string,
              startDate: Date,
              endDate: Date,
              cost: number,
              costType: string,
              owner: string,
              jobtype: string,
              isOffer: boolean) {
    this.title = title;
	this.description = description;
    this.description = description;
    this.owner = owner;
    this.location = location;
    this.startDate = startDate;
    this.endDate = endDate;
    this.occurrence = occurrence;
    this.cost = cost;
    this.costType = costType;
    this.jobType = jobtype;
    this.isOffer = isOffer;
  }
}
