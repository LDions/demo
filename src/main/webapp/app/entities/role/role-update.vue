<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.role.home.createOrEditLabel" data-cy="RoleCreateUpdateHeading">Create or edit a Role</h2>
        <div>
          <div class="form-group" v-if="role.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="role.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="role-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.role.createdBy.$invalid, invalid: $v.role.createdBy.$invalid }"
              v-model="$v.role.createdBy.$model"
              required
            />
            <div v-if="$v.role.createdBy.$anyDirty && $v.role.createdBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.createdBy.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.role.createdBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-createdTime">Created Time</label>
            <div class="d-flex">
              <input
                id="role-createdTime"
                data-cy="createdTime"
                type="datetime-local"
                class="form-control"
                name="createdTime"
                :class="{ valid: !$v.role.createdTime.$invalid, invalid: $v.role.createdTime.$invalid }"
                required
                :value="convertDateTimeFromServer($v.role.createdTime.$model)"
                @change="updateInstantField('createdTime', $event)"
              />
            </div>
            <div v-if="$v.role.createdTime.$anyDirty && $v.role.createdTime.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.createdTime.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.role.createdTime.ZonedDateTimelocal">
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-updatedBy">Updated By</label>
            <input
              type="text"
              class="form-control"
              name="updatedBy"
              id="role-updatedBy"
              data-cy="updatedBy"
              :class="{ valid: !$v.role.updatedBy.$invalid, invalid: $v.role.updatedBy.$invalid }"
              v-model="$v.role.updatedBy.$model"
            />
            <div v-if="$v.role.updatedBy.$anyDirty && $v.role.updatedBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.updatedBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-updatedTime">Updated Time</label>
            <div class="d-flex">
              <input
                id="role-updatedTime"
                data-cy="updatedTime"
                type="datetime-local"
                class="form-control"
                name="updatedTime"
                :class="{ valid: !$v.role.updatedTime.$invalid, invalid: $v.role.updatedTime.$invalid }"
                :value="convertDateTimeFromServer($v.role.updatedTime.$model)"
                @change="updateInstantField('updatedTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-deleted">Deleted</label>
            <input
              type="checkbox"
              class="form-check"
              name="deleted"
              id="role-deleted"
              data-cy="deleted"
              :class="{ valid: !$v.role.deleted.$invalid, invalid: $v.role.deleted.$invalid }"
              v-model="$v.role.deleted.$model"
              required
            />
            <div v-if="$v.role.deleted.$anyDirty && $v.role.deleted.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.deleted.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-roleName">Role Name</label>
            <input
              type="text"
              class="form-control"
              name="roleName"
              id="role-roleName"
              data-cy="roleName"
              :class="{ valid: !$v.role.roleName.$invalid, invalid: $v.role.roleName.$invalid }"
              v-model="$v.role.roleName.$model"
              required
            />
            <div v-if="$v.role.roleName.$anyDirty && $v.role.roleName.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.roleName.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.role.roleName.maxLength">
                This field cannot be longer than 128 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-roleCode">Role Code</label>
            <input
              type="text"
              class="form-control"
              name="roleCode"
              id="role-roleCode"
              data-cy="roleCode"
              :class="{ valid: !$v.role.roleCode.$invalid, invalid: $v.role.roleCode.$invalid }"
              v-model="$v.role.roleCode.$model"
              required
            />
            <div v-if="$v.role.roleCode.$anyDirty && $v.role.roleCode.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.roleCode.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.role.roleCode.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-status">Status</label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="role-status"
              data-cy="status"
              :class="{ valid: !$v.role.status.$invalid, invalid: $v.role.status.$invalid }"
              v-model="$v.role.status.$model"
              required
            />
            <div v-if="$v.role.status.$anyDirty && $v.role.status.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.status.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.role.status.maxLength">
                This field cannot be longer than 32 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="role-inlay">Inlay</label>
            <input
              type="checkbox"
              class="form-check"
              name="inlay"
              id="role-inlay"
              data-cy="inlay"
              :class="{ valid: !$v.role.inlay.$invalid, invalid: $v.role.inlay.$invalid }"
              v-model="$v.role.inlay.$model"
              required
            />
            <div v-if="$v.role.inlay.$anyDirty && $v.role.inlay.$invalid">
              <small class="form-text text-danger" v-if="!$v.role.inlay.required"> This field is required. </small>
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
            :disabled="$v.role.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./role-update.component.ts"></script>
