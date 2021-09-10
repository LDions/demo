export interface IUsers {
  id?: number;
  createdBy?: string;
  createdTime?: Date;
  updatedBy?: string | null;
  updatedTime?: Date | null;
  deleted?: boolean;
  name?: string;
  sex?: string;
  password?: string;
  state?: string;
  avatar?: string | null;
  resetCode?: string | null;
  resetTime?: Date | null;
  userCode?: string;
  phone?: string | null;
  activate?: string | null;
  email?: string | null;
}

export class Users implements IUsers {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdTime?: Date,
    public updatedBy?: string | null,
    public updatedTime?: Date | null,
    public deleted?: boolean,
    public name?: string,
    public sex?: string,
    public password?: string,
    public state?: string,
    public avatar?: string | null,
    public resetCode?: string | null,
    public resetTime?: Date | null,
    public userCode?: string,
    public phone?: string | null,
    public activate?: string | null,
    public email?: string | null
  ) {
    this.deleted = this.deleted ?? false;
  }
}
