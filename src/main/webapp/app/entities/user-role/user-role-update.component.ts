import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import { IUserRole, UserRole } from '@/shared/model/user-role.model';
import UserRoleService from './user-role.service';

const validations: any = {
  userRole: {
    userId: {
      required,
      numeric,
    },
    roleId: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class UserRoleUpdate extends Vue {
  @Inject('userRoleService') private userRoleService: () => UserRoleService;
  public userRole: IUserRole = new UserRole();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userRoleId) {
        vm.retrieveUserRole(to.params.userRoleId);
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
    if (this.userRole.id) {
      this.userRoleService()
        .update(this.userRole)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A UserRole is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.userRoleService()
        .create(this.userRole)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A UserRole is created with identifier ' + param.id;
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

  public retrieveUserRole(userRoleId): void {
    this.userRoleService()
      .find(userRoleId)
      .then(res => {
        this.userRole = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
