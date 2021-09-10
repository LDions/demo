<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.userRole.home.createOrEditLabel" data-cy="UserRoleCreateUpdateHeading">Create or edit a UserRole</h2>
        <div>
          <div class="form-group" v-if="userRole.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="userRole.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="user-role-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="user-role-userId"
              data-cy="userId"
              :class="{ valid: !$v.userRole.userId.$invalid, invalid: $v.userRole.userId.$invalid }"
              v-model.number="$v.userRole.userId.$model"
              required
            />
            <div v-if="$v.userRole.userId.$anyDirty && $v.userRole.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.userRole.userId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.userRole.userId.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="user-role-roleId">Role Id</label>
            <input
              type="number"
              class="form-control"
              name="roleId"
              id="user-role-roleId"
              data-cy="roleId"
              :class="{ valid: !$v.userRole.roleId.$invalid, invalid: $v.userRole.roleId.$invalid }"
              v-model.number="$v.userRole.roleId.$model"
              required
            />
            <div v-if="$v.userRole.roleId.$anyDirty && $v.userRole.roleId.$invalid">
              <small class="form-text text-danger" v-if="!$v.userRole.roleId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.userRole.roleId.numeric"> This field should be a number. </small>
            </div>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.userRole.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./user-role-update.component.ts"></script>
