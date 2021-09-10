export interface IUserRole {
  id?: number;
  userId?: number;
  roleId?: number;
}

export class UserRole implements IUserRole {
  constructor(public id?: number, public userId?: number, public roleId?: number) {}
}
