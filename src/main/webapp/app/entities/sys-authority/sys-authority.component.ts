import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysAuthority } from '@/shared/model/sys-authority.model';

import SysAuthorityService from './sys-authority.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysAuthority extends Vue {
  @Inject('sysAuthorityService') private sysAuthorityService: () => SysAuthorityService;
  private removeId: number = null;

  public sysAuthorities: ISysAuthority[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysAuthoritys();
  }

  public clear(): void {
    this.retrieveAllSysAuthoritys();
  }

  public retrieveAllSysAuthoritys(): void {
    this.isFetching = true;

    this.sysAuthorityService()
      .retrieve()
      .then(
        res => {
          this.sysAuthorities = res.data;
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

  public prepareRemove(instance: ISysAuthority): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysAuthority(): void {
    this.sysAuthorityService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysAuthority is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllSysAuthoritys();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
