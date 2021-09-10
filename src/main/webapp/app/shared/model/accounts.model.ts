export interface IAccounts {
  id?: number;
  createdBy?: string;
  createdTime?: Date;
  updatedBy?: string | null;
  updatedTime?: Date | null;
  deleted?: boolean;
  categories?: string;
  code?: string | null;
  openId?: string;
  accessToken?: string;
  userid?: number;
}

export class Accounts implements IAccounts {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdTime?: Date,
    public updatedBy?: string | null,
    public updatedTime?: Date | null,
    public deleted?: boolean,
    public categories?: string,
    public code?: string | null,
    public openId?: string,
    public accessToken?: string,
    public userid?: number
  ) {
    this.deleted = this.deleted ?? false;
  }
}
