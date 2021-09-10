import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import { IUsers, Users } from '@/shared/model/users.model';
import UsersService from './users.service';

const validations: any = {
  users: {
    createdBy: {
      required,
      maxLength: maxLength(64),
    },
    createdTime: {
      required,
    },
    updatedBy: {
      maxLength: maxLength(64),
    },
    updatedTime: {},
    deleted: {
      required,
    },
    name: {
      required,
      maxLength: maxLength(128),
    },
    sex: {
      required,
      maxLength: maxLength(32),
    },
    password: {
      required,
      maxLength: maxLength(512),
    },
    state: {
      required,
      maxLength: maxLength(32),
    },
    avatar: {
      maxLength: maxLength(2000),
    },
    resetCode: {
      maxLength: maxLength(64),
    },
    resetTime: {},
    userCode: {
      required,
      maxLength: maxLength(64),
    },
    phone: {
      maxLength: maxLength(11),
    },
    activate: {
      maxLength: maxLength(64),
    },
    email: {
      maxLength: maxLength(320),
    },
  },
};

@Component({
  validations,
})
export default class UsersUpdate extends Vue {
  @Inject('usersService') private usersService: () => UsersService;
  public users: IUsers = new Users();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.usersId) {
        vm.retrieveUsers(to.params.usersId);
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
    if (this.users.id) {
      this.usersService()
        .update(this.users)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Users is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.usersService()
        .create(this.users)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Users is created with identifier ' + param.id;
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.users[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.users[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.users[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.users[field] = null;
    }
  }

  public retrieveUsers(usersId): void {
    this.usersService()
      .find(usersId)
      .then(res => {
        res.createdTime = new Date(res.createdTime);
        res.updatedTime = new Date(res.updatedTime);
        res.resetTime = new Date(res.resetTime);
        this.users = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
