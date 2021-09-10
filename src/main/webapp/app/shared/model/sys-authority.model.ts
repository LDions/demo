export interface ISysAuthority {
  id?: number;
  authorityName?: string;
  authorityKey?: string | null;
  parentId?: number;
  authorityType?: string;
}

export class SysAuthority implements ISysAuthority {
  constructor(
    public id?: number,
    public authorityName?: string,
    public authorityKey?: string | null,
    public parentId?: number,
    public authorityType?: string
  ) {}
}
