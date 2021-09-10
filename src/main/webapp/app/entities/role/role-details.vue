<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="role">
        <h2 class="jh-entity-heading" data-cy="roleDetailsHeading"><span>Role</span> {{ role.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Created By</span>
          </dt>
          <dd>
            <span>{{ role.createdBy }}</span>
          </dd>
          <dt>
            <span>Created Time</span>
          </dt>
          <dd>
            <span>{{ role.createdTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Updated By</span>
          </dt>
          <dd>
            <span>{{ role.updatedBy }}</span>
          </dd>
          <dt>
            <span>Updated Time</span>
          </dt>
          <dd>
            <span>{{ role.updatedTime | formatDate }}</span>
          </dd>
          <dt>
            <span>Deleted</span>
          </dt>
          <dd>
            <span>{{ role.deleted }}</span>
          </dd>
          <dt>
            <span>Role Name</span>
          </dt>
          <dd>
            <span>{{ role.roleName }}</span>
          </dd>
          <dt>
            <span>Role Code</span>
          </dt>
          <dd>
            <span>{{ role.roleCode }}</span>
          </dd>
          <dt>
            <span>Status</span>
          </dt>
          <dd>
            <span>{{ role.status }}</span>
          </dd>
          <dt>
            <span>Inlay</span>
          </dt>
          <dd>
            <span>{{ role.inlay }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="role.id" :to="{ name: 'RoleEdit', params: { roleId: role.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./role-details.component.ts"></script>
