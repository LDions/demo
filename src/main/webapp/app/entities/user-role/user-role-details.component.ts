import { Component, Vue, Inject } from 'vue-property-decorator';

import { IUserRole } from '@/shared/model/user-role.model';
import UserRoleService from './user-role.service';

@Component
export default class UserRoleDetails extends Vue {
  @Inject('userRoleService') private userRoleService: () => UserRoleService;
  public userRole: IUserRole = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.userRoleId) {
        vm.retrieveUserRole(to.params.userRoleId);
      }
    });
  }

  public retrieveUserRole(userRoleId) {
    this.userRoleService()
      .find(userRoleId)
      .then(res => {
        this.userRole = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
