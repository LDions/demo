<template>
  <div>
    <h2 id="page-heading" data-cy="SysAuthorityHeading">
      <span id="sys-authority-heading">Sys Authorities</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'SysAuthorityCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sys-authority"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Sys Authority </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sysAuthorities && sysAuthorities.length === 0">
      <span>No sysAuthorities found</span>
    </div>
    <div class="table-responsive" v-if="sysAuthorities && sysAuthorities.length > 0">
      <table class="table table-striped" aria-describedby="sysAuthorities">
        <thead>
          <tr>
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Authority Name</span></th>
            <th scope="row"><span>Authority Key</span></th>
            <th scope="row"><span>Parent Id</span></th>
            <th scope="row"><span>Authority Type</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sysAuthority in sysAuthorities" :key="sysAuthority.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SysAuthorityView', params: { sysAuthorityId: sysAuthority.id } }">{{
                sysAuthority.id
              }}</router-link>
            </td>
            <td>{{ sysAuthority.authorityName }}</td>
            <td>{{ sysAuthority.authorityKey }}</td>
            <td>{{ sysAuthority.parentId }}</td>
            <td>{{ sysAuthority.authorityType }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SysAuthorityView', params: { sysAuthorityId: sysAuthority.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SysAuthorityEdit', params: { sysAuthorityId: sysAuthority.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sysAuthority)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="demoApp.sysAuthority.delete.question" data-cy="sysAuthorityDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-sysAuthority-heading">Are you sure you want to delete this Sys Authority?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-sysAuthority"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeSysAuthority()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sys-authority.component.ts"></script>
