import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Accounts = () => import('@/entities/accounts/accounts.vue');
// prettier-ignore
const AccountsUpdate = () => import('@/entities/accounts/accounts-update.vue');
// prettier-ignore
const AccountsDetails = () => import('@/entities/accounts/accounts-details.vue');
// prettier-ignore
const Role = () => import('@/entities/role/role.vue');
// prettier-ignore
const RoleUpdate = () => import('@/entities/role/role-update.vue');
// prettier-ignore
const RoleDetails = () => import('@/entities/role/role-details.vue');
// prettier-ignore
const SysAuthority = () => import('@/entities/sys-authority/sys-authority.vue');
// prettier-ignore
const SysAuthorityUpdate = () => import('@/entities/sys-authority/sys-authority-update.vue');
// prettier-ignore
const SysAuthorityDetails = () => import('@/entities/sys-authority/sys-authority-details.vue');
// prettier-ignore
const UserRole = () => import('@/entities/user-role/user-role.vue');
// prettier-ignore
const UserRoleUpdate = () => import('@/entities/user-role/user-role-update.vue');
// prettier-ignore
const UserRoleDetails = () => import('@/entities/user-role/user-role-details.vue');
// prettier-ignore
const RoleAuthority = () => import('@/entities/role-authority/role-authority.vue');
// prettier-ignore
const RoleAuthorityUpdate = () => import('@/entities/role-authority/role-authority-update.vue');
// prettier-ignore
const RoleAuthorityDetails = () => import('@/entities/role-authority/role-authority-details.vue');
// prettier-ignore
const Users = () => import('@/entities/users/users.vue');
// prettier-ignore
const UsersUpdate = () => import('@/entities/users/users-update.vue');
// prettier-ignore
const UsersDetails = () => import('@/entities/users/users-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/accounts',
    name: 'Accounts',
    component: Accounts,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accounts/new',
    name: 'AccountsCreate',
    component: AccountsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accounts/:accountsId/edit',
    name: 'AccountsEdit',
    component: AccountsUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/accounts/:accountsId/view',
    name: 'AccountsView',
    component: AccountsDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role',
    name: 'Role',
    component: Role,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role/new',
    name: 'RoleCreate',
    component: RoleUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role/:roleId/edit',
    name: 'RoleEdit',
    component: RoleUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role/:roleId/view',
    name: 'RoleView',
    component: RoleDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/sys-authority',
    name: 'SysAuthority',
    component: SysAuthority,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/sys-authority/new',
    name: 'SysAuthorityCreate',
    component: SysAuthorityUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/sys-authority/:sysAuthorityId/edit',
    name: 'SysAuthorityEdit',
    component: SysAuthorityUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/sys-authority/:sysAuthorityId/view',
    name: 'SysAuthorityView',
    component: SysAuthorityDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-role',
    name: 'UserRole',
    component: UserRole,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-role/new',
    name: 'UserRoleCreate',
    component: UserRoleUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-role/:userRoleId/edit',
    name: 'UserRoleEdit',
    component: UserRoleUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/user-role/:userRoleId/view',
    name: 'UserRoleView',
    component: UserRoleDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role-authority',
    name: 'RoleAuthority',
    component: RoleAuthority,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role-authority/new',
    name: 'RoleAuthorityCreate',
    component: RoleAuthorityUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role-authority/:roleAuthorityId/edit',
    name: 'RoleAuthorityEdit',
    component: RoleAuthorityUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/role-authority/:roleAuthorityId/view',
    name: 'RoleAuthorityView',
    component: RoleAuthorityDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users',
    name: 'Users',
    component: Users,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/new',
    name: 'UsersCreate',
    component: UsersUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/:usersId/edit',
    name: 'UsersEdit',
    component: UsersUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/:usersId/view',
    name: 'UsersView',
    component: UsersDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
