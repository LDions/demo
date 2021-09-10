<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sysAuthority">
        <h2 class="jh-entity-heading" data-cy="sysAuthorityDetailsHeading"><span>SysAuthority</span> {{ sysAuthority.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Authority Name</span>
          </dt>
          <dd>
            <span>{{ sysAuthority.authorityName }}</span>
          </dd>
          <dt>
            <span>Authority Key</span>
          </dt>
          <dd>
            <span>{{ sysAuthority.authorityKey }}</span>
          </dd>
          <dt>
            <span>Parent Id</span>
          </dt>
          <dd>
            <span>{{ sysAuthority.parentId }}</span>
          </dd>
          <dt>
            <span>Authority Type</span>
          </dt>
          <dd>
            <span>{{ sysAuthority.authorityType }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link
          v-if="sysAuthority.id"
          :to="{ name: 'SysAuthorityEdit', params: { sysAuthorityId: sysAuthority.id } }"
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

<script lang="ts" src="./sys-authority-details.component.ts"></script>
