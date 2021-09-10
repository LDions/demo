import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength, numeric } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import { IAccounts, Accounts } from '@/shared/model/accounts.model';
import AccountsService from './accounts.service';

const validations: any = {
  accounts: {
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
    categories: {
      required,
      maxLength: maxLength(32),
    },
    code: {
      maxLength: maxLength(64),
    },
    openId: {
      required,
      maxLength: maxLength(64),
    },
    accessToken: {
      required,
      maxLength: maxLength(64),
    },
    userid: {
      required,
      numeric,
    },
  },
};

@Component({
  validations,
})
export default class AccountsUpdate extends Vue {
  @Inject('accountsService') private accountsService: () => AccountsService;
  public accounts: IAccounts = new Accounts();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.accountsId) {
        vm.retrieveAccounts(to.params.accountsId);
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
    if (this.accounts.id) {
      this.accountsService()
        .update(this.accounts)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Accounts is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.accountsService()
        .create(this.accounts)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Accounts is created with identifier ' + param.id;
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
      this.accounts[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.accounts[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.accounts[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.accounts[field] = null;
    }
  }

  public retrieveAccounts(accountsId): void {
    this.accountsService()
      .find(accountsId)
      .then(res => {
        res.createdTime = new Date(res.createdTime);
        res.updatedTime = new Date(res.updatedTime);
        this.accounts = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
