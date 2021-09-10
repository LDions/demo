import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength, numeric } from 'vuelidate/lib/validators';

import { ISysAuthority, SysAuthority } from '@/shared/model/sys-authority.model';
import SysAuthorityService from './sys-authority.service';

const validations: any = {
  sysAuthority: {
    authorityName: {
      required,
      maxLength: maxLength(128),
    },
    authorityKey: {
      maxLength: maxLength(64),
    },
    parentId: {
      required,
      numeric,
    },
    authorityType: {
      required,
      maxLength: maxLength(32),
    },
  },
};

@Component({
  validations,
})
export default class SysAuthorityUpdate extends Vue {
  @Inject('sysAuthorityService') private sysAuthorityService: () => SysAuthorityService;
  public sysAuthority: ISysAuthority = new SysAuthority();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysAuthorityId) {
        vm.retrieveSysAuthority(to.params.sysAuthorityId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysAuthority.id) {
      this.sysAuthorityService()
        .update(this.sysAuthority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysAuthority is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.sysAuthorityService()
        .create(this.sysAuthority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysAuthority is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveSysAuthority(sysAuthorityId): void {
    this.sysAuthorityService()
      .find(sysAuthorityId)
      .then(res => {
        this.sysAuthority = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
