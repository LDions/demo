import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IUsers } from '@/shared/model/users.model';

import UsersService from './users.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Users extends Vue {
  @Inject('usersService') private usersService: () => UsersService;
  private removeId: number = null;

  public users: IUsers[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllUserss();
  }

  public clear(): void {
    this.retrieveAllUserss();
  }

  public retrieveAllUserss(): void {
    this.isFetching = true;

    this.usersService()
      .retrieve()
      .then(
        res => {
          this.users = res.data;
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

  public prepareRemove(instance: IUsers): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeUsers(): void {
    this.usersService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Users is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllUserss();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
