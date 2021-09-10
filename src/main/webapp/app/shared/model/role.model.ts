export interface IRole {
  id?: number;
  createdBy?: string;
  createdTime?: Date;
  updatedBy?: string | null;
  updatedTime?: Date | null;
  deleted?: boolean;
  roleName?: string;
  roleCode?: string;
  status?: string;
  inlay?: boolean;
}

export class Role implements IRole {
  constructor(
    public id?: number,
    public createdBy?: string,
    public createdTime?: Date,
    public updatedBy?: string | null,
    public updatedTime?: Date | null,
    public deleted?: boolean,
    public roleName?: string,
    public roleCode?: string,
    public status?: string,
    public inlay?: boolean
  ) {
    this.deleted = this.deleted ?? false;
    this.inlay = this.inlay ?? false;
  }
}
