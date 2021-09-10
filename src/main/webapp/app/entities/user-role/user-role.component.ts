import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUserRole } from '@/shared/model/user-role.model';

import UserRoleService from './user-role.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class UserRole extends Vue {
  @Inject('userRoleService') private userRoleService: () => UserRoleService;
  private removeId: number = null;

  public userRoles: IUserRole[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserRoles();
  }

  public clear(): void {
    this.retrieveAllUserRoles();
  }

  public retrieveAllUserRoles(): void {
    this.isFetching = true;

    this.userRoleService()
      .retrieve()
      .then(
        res => {
          this.userRoles = res.data;
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

  public prepareRemove(instance: IUserRole): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUserRole(): void {
    this.userRoleService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A UserRole is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserRoles();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
