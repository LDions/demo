import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IRole } from '@/shared/model/role.model';

import RoleService from './role.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Role extends Vue {
  @Inject('roleService') private roleService: () => RoleService;
  private removeId: number = null;

  public roles: IRole[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllRoles();
  }

  public clear(): void {
    this.retrieveAllRoles();
  }

  public retrieveAllRoles(): void {
    this.isFetching = true;

    this.roleService()
      .retrieve()
      .then(
        res => {
          this.roles = res.data;
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

  public prepareRemove(instance: IRole): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeRole(): void {
    this.roleService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Role is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllRoles();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
