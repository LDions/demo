<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="userRole">
        <h2 class="jh-entity-heading" data-cy="userRoleDetailsHeading"><span>UserRole</span> {{ userRole.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>User Id</span>
          </dt>
          <dd>
            <span>{{ userRole.userId }}</span>
          </dd>
          <dt>
            <span>Role Id</span>
          </dt>
          <dd>
            <span>{{ userRole.roleId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="userRole.id" :to="{ name: 'UserRoleEdit', params: { userRoleId: userRole.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./user-role-details.component.ts"></script>
