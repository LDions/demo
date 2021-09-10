import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRoleAuthority } from '@/shared/model/role-authority.model';

import RoleAuthorityService from './role-authority.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class RoleAuthority extends Vue {
  @Inject('roleAuthorityService') private roleAuthorityService: () => RoleAuthorityService;
  private removeId: number = null;

  public roleAuthorities: IRoleAuthority[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRoleAuthoritys();
  }

  public clear(): void {
    this.retrieveAllRoleAuthoritys();
  }

  public retrieveAllRoleAuthoritys(): void {
    this.isFetching = true;

    this.roleAuthorityService()
      .retrieve()
      .then(
        res => {
          this.roleAuthorities = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IRoleAuthority): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRoleAuthority(): void {
    this.roleAuthorityService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A RoleAuthority is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRoleAuthoritys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
