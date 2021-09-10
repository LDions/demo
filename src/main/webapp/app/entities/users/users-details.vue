<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="users">
        <h2 class="jh-entity-heading" data-cy="usersDetailsHeading"><span>Users</span> {{ users.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Created By</span>
          </dt>
          <dd>
            <span>{{ users.createdBy }}</span>
          </dd>
          <dt>
            <span>Created Time</span>
          </dt>
          <dd>
            <span>{{ users.createdTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Updated By</span>
          </dt>
          <dd>
            <span>{{ users.updatedBy }}</span>
          </dd>
          <dt>
            <span>Updated Time</span>
          </dt>
          <dd>
            <span>{{ users.updatedTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Deleted</span>
          </dt>
          <dd>
            <span>{{ users.deleted }}</span>
          </dd>
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ users.name }}</span>
          </dd>
          <dt>
            <span>Sex</span>
          </dt>
          <dd>
            <span>{{ users.sex }}</span>
          </dd>
          <dt>
            <span>Password</span>
          </dt>
          <dd>
            <span>{{ users.password }}</span>
          </dd>
          <dt>
            <span>State</span>
          </dt>
          <dd>
            <span>{{ users.state }}</span>
          </dd>
          <dt>
            <span>Avatar</span>
          </dt>
          <dd>
            <span>{{ users.avatar }}</span>
          </dd>
          <dt>
            <span>Reset Code</span>
          </dt>
          <dd>
            <span>{{ users.resetCode }}</span>
          </dd>
          <dt>
            <span>Reset Time</span>
          </dt>
          <dd>
            <span>{{ users.resetTime | formatDate }}</span>
          </dd>
          <dt>
            <span>User Code</span>
          </dt>
          <dd>
            <span>{{ users.userCode }}</span>
          </dd>
          <dt>
            <span>Phone</span>
          </dt>
          <dd>
            <span>{{ users.phone }}</span>
          </dd>
          <dt>
            <span>Activate</span>
          </dt>
          <dd>
            <span>{{ users.activate }}</span>
          </dd>
          <dt>
            <span>Email</span>
          </dt>
          <dd>
            <span>{{ users.email }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="users.id" :to="{ name: 'UsersEdit', params: { usersId: users.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./users-details.component.ts"></script>
