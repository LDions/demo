import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import { IRoleAuthority, RoleAuthority } from '@/shared/model/role-authority.model';
import RoleAuthorityService from './role-authority.service';

const validations: any = {
  roleAuthority: {
    roleId: {
      required,
      numeric,
    },
    authorityId: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class RoleAuthorityUpdate extends Vue {
  @Inject('roleAuthorityService') private roleAuthorityService: () => RoleAuthorityService;
  public roleAuthority: IRoleAuthority = new RoleAuthority();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleAuthorityId) {
        vm.retrieveRoleAuthority(to.params.roleAuthorityId);
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
    if (this.roleAuthority.id) {
      this.roleAuthorityService()
        .update(this.roleAuthority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RoleAuthority is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.roleAuthorityService()
        .create(this.roleAuthority)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A RoleAuthority is created with identifier ' + param.id;
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

  public retrieveRoleAuthority(roleAuthorityId): void {
    this.roleAuthorityService()
      .find(roleAuthorityId)
      .then(res => {
        this.roleAuthority = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
