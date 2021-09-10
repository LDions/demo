<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="roleAuthority">
        <h2 class="jh-entity-heading" data-cy="roleAuthorityDetailsHeading"><span>RoleAuthority</span> {{ roleAuthority.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Role Id</span>
          </dt>
          <dd>
            <span>{{ roleAuthority.roleId }}</span>
          </dd>
          <dt>
            <span>Authority Id</span>
          </dt>
          <dd>
            <span>{{ roleAuthority.authorityId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="roleAuthority.id"
          :to="{ name: 'RoleAuthorityEdit', params: { roleAuthorityId: roleAuthority.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./role-authority-details.component.ts"></script>
