import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAccounts } from '@/shared/model/accounts.model';

import AccountsService from './accounts.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Accounts extends Vue {
  @Inject('accountsService') private accountsService: () => AccountsService;
  private removeId: number = null;

  public accounts: IAccounts[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllAccountss();
  }

  public clear(): void {
    this.retrieveAllAccountss();
  }

  public retrieveAllAccountss(): void {
    this.isFetching = true;

    this.accountsService()
      .retrieve()
      .then(
        res => {
          this.accounts = res.data;
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

  public prepareRemove(instance: IAccounts): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeAccounts(): void {
    this.accountsService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Accounts is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllAccountss();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
