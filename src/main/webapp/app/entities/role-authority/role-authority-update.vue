<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.roleAuthority.home.createOrEditLabel" data-cy="RoleAuthorityCreateUpdateHeading">Create or edit a RoleAuthority</h2>
        <div>
          <div class="form-group" v-if="roleAuthority.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="roleAuthority.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-authority-roleId">Role Id</label>
            <input
              type="number"
              class="form-control"
              name="roleId"
              id="role-authority-roleId"
              data-cy="roleId"
              :class="{ valid: !$v.roleAuthority.roleId.$invalid, invalid: $v.roleAuthority.roleId.$invalid }"
              v-model.number="$v.roleAuthority.roleId.$model"
              required
            />
            <div v-if="$v.roleAuthority.roleId.$anyDirty && $v.roleAuthority.roleId.$invalid">
              <small class="form-text text-danger" v-if="!$v.roleAuthority.roleId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.roleAuthority.roleId.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-authority-authorityId">Authority Id</label>
            <input
              type="number"
              class="form-control"
              name="authorityId"
              id="role-authority-authorityId"
              data-cy="authorityId"
              :class="{ valid: !$v.roleAuthority.authorityId.$invalid, invalid: $v.roleAuthority.authorityId.$invalid }"
              v-model.number="$v.roleAuthority.authorityId.$model"
              required
            />
            <div v-if="$v.roleAuthority.authorityId.$anyDirty && $v.roleAuthority.authorityId.$invalid">
              <small class="form-text text-danger" v-if="!$v.roleAuthority.authorityId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.roleAuthority.authorityId.numeric"> This field should be a number. </small>
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
            :disabled="$v.roleAuthority.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./role-authority-update.component.ts"></script>
