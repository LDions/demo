<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.users.home.createOrEditLabel" data-cy="UsersCreateUpdateHeading">Create or edit a Users</h2>
        <div>
          <div class="form-group" v-if="users.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="users.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="users-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.users.createdBy.$invalid, invalid: $v.users.createdBy.$invalid }"
              v-model="$v.users.createdBy.$model"
              required
            />
            <div v-if="$v.users.createdBy.$anyDirty && $v.users.createdBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.createdBy.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.createdBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-createdTime">Created Time</label>
            <div class="d-flex">
              <input
                id="users-createdTime"
                data-cy="createdTime"
                type="datetime-local"
                class="form-control"
                name="createdTime"
                :class="{ valid: !$v.users.createdTime.$invalid, invalid: $v.users.createdTime.$invalid }"
                required
                :value="convertDateTimeFromServer($v.users.createdTime.$model)"
                @change="updateInstantField('createdTime', $event)"
              />
            </div>
            <div v-if="$v.users.createdTime.$anyDirty && $v.users.createdTime.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.createdTime.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.createdTime.ZonedDateTimelocal">
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-updatedBy">Updated By</label>
            <input
              type="text"
              class="form-control"
              name="updatedBy"
              id="users-updatedBy"
              data-cy="updatedBy"
              :class="{ valid: !$v.users.updatedBy.$invalid, invalid: $v.users.updatedBy.$invalid }"
              v-model="$v.users.updatedBy.$model"
            />
            <div v-if="$v.users.updatedBy.$anyDirty && $v.users.updatedBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.updatedBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-updatedTime">Updated Time</label>
            <div class="d-flex">
              <input
                id="users-updatedTime"
                data-cy="updatedTime"
                type="datetime-local"
                class="form-control"
                name="updatedTime"
                :class="{ valid: !$v.users.updatedTime.$invalid, invalid: $v.users.updatedTime.$invalid }"
                :value="convertDateTimeFromServer($v.users.updatedTime.$model)"
                @change="updateInstantField('updatedTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-deleted">Deleted</label>
            <input
              type="checkbox"
              class="form-check"
              name="deleted"
              id="users-deleted"
              data-cy="deleted"
              :class="{ valid: !$v.users.deleted.$invalid, invalid: $v.users.deleted.$invalid }"
              v-model="$v.users.deleted.$model"
              required
            />
            <div v-if="$v.users.deleted.$anyDirty && $v.users.deleted.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.deleted.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="users-name"
              data-cy="name"
              :class="{ valid: !$v.users.name.$invalid, invalid: $v.users.name.$invalid }"
              v-model="$v.users.name.$model"
              required
            />
            <div v-if="$v.users.name.$anyDirty && $v.users.name.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.name.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.name.maxLength">
                This field cannot be longer than 128 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-sex">Sex</label>
            <input
              type="text"
              class="form-control"
              name="sex"
              id="users-sex"
              data-cy="sex"
              :class="{ valid: !$v.users.sex.$invalid, invalid: $v.users.sex.$invalid }"
              v-model="$v.users.sex.$model"
              required
            />
            <div v-if="$v.users.sex.$anyDirty && $v.users.sex.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.sex.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.sex.maxLength"> This field cannot be longer than 32 characters. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-password">Password</label>
            <input
              type="text"
              class="form-control"
              name="password"
              id="users-password"
              data-cy="password"
              :class="{ valid: !$v.users.password.$invalid, invalid: $v.users.password.$invalid }"
              v-model="$v.users.password.$model"
              required
            />
            <div v-if="$v.users.password.$anyDirty && $v.users.password.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.password.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.password.maxLength">
                This field cannot be longer than 512 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-state">State</label>
            <input
              type="text"
              class="form-control"
              name="state"
              id="users-state"
              data-cy="state"
              :class="{ valid: !$v.users.state.$invalid, invalid: $v.users.state.$invalid }"
              v-model="$v.users.state.$model"
              required
            />
            <div v-if="$v.users.state.$anyDirty && $v.users.state.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.state.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.state.maxLength">
                This field cannot be longer than 32 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-avatar">Avatar</label>
            <input
              type="text"
              class="form-control"
              name="avatar"
              id="users-avatar"
              data-cy="avatar"
              :class="{ valid: !$v.users.avatar.$invalid, invalid: $v.users.avatar.$invalid }"
              v-model="$v.users.avatar.$model"
            />
            <div v-if="$v.users.avatar.$anyDirty && $v.users.avatar.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.avatar.maxLength">
                This field cannot be longer than 2000 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-resetCode">Reset Code</label>
            <input
              type="text"
              class="form-control"
              name="resetCode"
              id="users-resetCode"
              data-cy="resetCode"
              :class="{ valid: !$v.users.resetCode.$invalid, invalid: $v.users.resetCode.$invalid }"
              v-model="$v.users.resetCode.$model"
            />
            <div v-if="$v.users.resetCode.$anyDirty && $v.users.resetCode.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.resetCode.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-resetTime">Reset Time</label>
            <div class="d-flex">
              <input
                id="users-resetTime"
                data-cy="resetTime"
                type="datetime-local"
                class="form-control"
                name="resetTime"
                :class="{ valid: !$v.users.resetTime.$invalid, invalid: $v.users.resetTime.$invalid }"
                :value="convertDateTimeFromServer($v.users.resetTime.$model)"
                @change="updateInstantField('resetTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-userCode">User Code</label>
            <input
              type="text"
              class="form-control"
              name="userCode"
              id="users-userCode"
              data-cy="userCode"
              :class="{ valid: !$v.users.userCode.$invalid, invalid: $v.users.userCode.$invalid }"
              v-model="$v.users.userCode.$model"
              required
            />
            <div v-if="$v.users.userCode.$anyDirty && $v.users.userCode.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.userCode.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.users.userCode.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-phone">Phone</label>
            <input
              type="text"
              class="form-control"
              name="phone"
              id="users-phone"
              data-cy="phone"
              :class="{ valid: !$v.users.phone.$invalid, invalid: $v.users.phone.$invalid }"
              v-model="$v.users.phone.$model"
            />
            <div v-if="$v.users.phone.$anyDirty && $v.users.phone.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.phone.maxLength">
                This field cannot be longer than 11 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-activate">Activate</label>
            <input
              type="text"
              class="form-control"
              name="activate"
              id="users-activate"
              data-cy="activate"
              :class="{ valid: !$v.users.activate.$invalid, invalid: $v.users.activate.$invalid }"
              v-model="$v.users.activate.$model"
            />
            <div v-if="$v.users.activate.$anyDirty && $v.users.activate.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.activate.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="users-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="users-email"
              data-cy="email"
              :class="{ valid: !$v.users.email.$invalid, invalid: $v.users.email.$invalid }"
              v-model="$v.users.email.$model"
            />
            <div v-if="$v.users.email.$anyDirty && $v.users.email.$invalid">
              <small class="form-text text-danger" v-if="!$v.users.email.maxLength">
                This field cannot be longer than 320 characters.
              </small>
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
            :disabled="$v.users.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./users-update.component.ts"></script>
