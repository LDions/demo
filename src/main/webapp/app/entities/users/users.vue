<template>
  <div>
    <h2 id="page-heading" data-cy="UsersHeading">
      <span id="users-heading">Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'UsersCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-users"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Users </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && users && users.length === 0">
      <span>No users found</span>
    </div>
    <div class="table-responsive" v-if="users && users.length > 0">
      <table class="table table-striped" aria-describedby="users">
        <thead>
          <tr>
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Created By</span></th>
            <th scope="row"><span>Created Time</span></th>
            <th scope="row"><span>Updated By</span></th>
            <th scope="row"><span>Updated Time</span></th>
            <th scope="row"><span>Deleted</span></th>
            <th scope="row"><span>Name</span></th>
            <th scope="row"><span>Sex</span></th>
            <th scope="row"><span>Password</span></th>
            <th scope="row"><span>State</span></th>
            <th scope="row"><span>Avatar</span></th>
            <th scope="row"><span>Reset Code</span></th>
            <th scope="row"><span>Reset Time</span></th>
            <th scope="row"><span>User Code</span></th>
            <th scope="row"><span>Phone</span></th>
            <th scope="row"><span>Activate</span></th>
            <th scope="row"><span>Email</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="users in users" :key="users.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UsersView', params: { usersId: users.id } }">{{ users.id }}</router-link>
            </td>
            <td>{{ users.createdBy }}</td>
            <td>{{ users.createdTime | formatDate }}</td>
            <td>{{ users.updatedBy }}</td>
            <td>{{ users.updatedTime | formatDate }}</td>
            <td>{{ users.deleted }}</td>
            <td>{{ users.name }}</td>
            <td>{{ users.sex }}</td>
            <td>{{ users.password }}</td>
            <td>{{ users.state }}</td>
            <td>{{ users.avatar }}</td>
            <td>{{ users.resetCode }}</td>
            <td>{{ users.resetTime | formatDate }}</td>
            <td>{{ users.userCode }}</td>
            <td>{{ users.phone }}</td>
            <td>{{ users.activate }}</td>
            <td>{{ users.email }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UsersView', params: { usersId: users.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UsersEdit', params: { usersId: users.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(users)"
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
        ><span id="demoApp.users.delete.question" data-cy="usersDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-users-heading">Are you sure you want to delete this Users?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-users"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeUsers()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./users.component.ts"></script>
