import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysAuthority } from '@/shared/model/sys-authority.model';
import SysAuthorityService from './sys-authority.service';

@Component
export default class SysAuthorityDetails extends Vue {
  @Inject('sysAuthorityService') private sysAuthorityService: () => SysAuthorityService;
  public sysAuthority: ISysAuthority = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysAuthorityId) {
        vm.retrieveSysAuthority(to.params.sysAuthorityId);
      }
    });
  }

  public retrieveSysAuthority(sysAuthorityId) {
    this.sysAuthorityService()
      .find(sysAuthorityId)
      .then(res => {
        this.sysAuthority = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
