import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRoleAuthority } from '@/shared/model/role-authority.model';
import RoleAuthorityService from './role-authority.service';

@Component
export default class RoleAuthorityDetails extends Vue {
  @Inject('roleAuthorityService') private roleAuthorityService: () => RoleAuthorityService;
  public roleAuthority: IRoleAuthority = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.roleAuthorityId) {
        vm.retrieveRoleAuthority(to.params.roleAuthorityId);
      }
    });
  }

  public retrieveRoleAuthority(roleAuthorityId) {
    this.roleAuthorityService()
      .find(roleAuthorityId)
      .then(res => {
        this.roleAuthority = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
