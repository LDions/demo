export interface IRoleAuthority {
  id?: number;
  roleId?: number;
  authorityId?: number;
}

export class RoleAuthority implements IRoleAuthority {
  constructor(public id?: number, public roleId?: number, public authorityId?: number) {}
}
