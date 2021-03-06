import {NewPerson} from "./newPerson.model";

export class Person extends NewPerson {
  public id: number;
  public firstname: string;
  public lastname: string;
  public description: string;
  public picture: string;

  constructor(username?: string,
              password?: string,
              location?: string,
              id?: number,
              firstname?: string,
              lastname?: string,
              description?: string,
              picture?: string
              ) {
    super(username, password, location, "");
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.description = description;
    this.picture = picture;
  }
}
