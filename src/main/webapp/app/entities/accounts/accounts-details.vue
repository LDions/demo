<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="accounts">
        <h2 class="jh-entity-heading" data-cy="accountsDetailsHeading"><span>Accounts</span> {{ accounts.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Created By</span>
          </dt>
          <dd>
            <span>{{ accounts.createdBy }}</span>
          </dd>
          <dt>
            <span>Created Time</span>
          </dt>
          <dd>
            <span>{{ accounts.createdTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Updated By</span>
          </dt>
          <dd>
            <span>{{ accounts.updatedBy }}</span>
          </dd>
          <dt>
            <span>Updated Time</span>
          </dt>
          <dd>
            <span>{{ accounts.updatedTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Deleted</span>
          </dt>
          <dd>
            <span>{{ accounts.deleted }}</span>
          </dd>
          <dt>
            <span>Categories</span>
          </dt>
          <dd>
            <span>{{ accounts.categories }}</span>
          </dd>
          <dt>
            <span>Code</span>
          </dt>
          <dd>
            <span>{{ accounts.code }}</span>
          </dd>
          <dt>
            <span>Open Id</span>
          </dt>
          <dd>
            <span>{{ accounts.openId }}</span>
          </dd>
          <dt>
            <span>Access Token</span>
          </dt>
          <dd>
            <span>{{ accounts.accessToken }}</span>
          </dd>
          <dt>
            <span>Userid</span>
          </dt>
          <dd>
            <span>{{ accounts.userid }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="accounts.id" :to="{ name: 'AccountsEdit', params: { accountsId: accounts.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./accounts-details.component.ts"></script>
