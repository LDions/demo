<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.accounts.home.createOrEditLabel" data-cy="AccountsCreateUpdateHeading">Create or edit a Accounts</h2>
        <div>
          <div class="form-group" v-if="accounts.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="accounts.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-createdBy">Created By</label>
            <input
              type="text"
              class="form-control"
              name="createdBy"
              id="accounts-createdBy"
              data-cy="createdBy"
              :class="{ valid: !$v.accounts.createdBy.$invalid, invalid: $v.accounts.createdBy.$invalid }"
              v-model="$v.accounts.createdBy.$model"
              required
            />
            <div v-if="$v.accounts.createdBy.$anyDirty && $v.accounts.createdBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.createdBy.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.createdBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-createdTime">Created Time</label>
            <div class="d-flex">
              <input
                id="accounts-createdTime"
                data-cy="createdTime"
                type="datetime-local"
                class="form-control"
                name="createdTime"
                :class="{ valid: !$v.accounts.createdTime.$invalid, invalid: $v.accounts.createdTime.$invalid }"
                required
                :value="convertDateTimeFromServer($v.accounts.createdTime.$model)"
                @change="updateInstantField('createdTime', $event)"
              />
            </div>
            <div v-if="$v.accounts.createdTime.$anyDirty && $v.accounts.createdTime.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.createdTime.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.createdTime.ZonedDateTimelocal">
                This field should be a date and time.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-updatedBy">Updated By</label>
            <input
              type="text"
              class="form-control"
              name="updatedBy"
              id="accounts-updatedBy"
              data-cy="updatedBy"
              :class="{ valid: !$v.accounts.updatedBy.$invalid, invalid: $v.accounts.updatedBy.$invalid }"
              v-model="$v.accounts.updatedBy.$model"
            />
            <div v-if="$v.accounts.updatedBy.$anyDirty && $v.accounts.updatedBy.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.updatedBy.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-updatedTime">Updated Time</label>
            <div class="d-flex">
              <input
                id="accounts-updatedTime"
                data-cy="updatedTime"
                type="datetime-local"
                class="form-control"
                name="updatedTime"
                :class="{ valid: !$v.accounts.updatedTime.$invalid, invalid: $v.accounts.updatedTime.$invalid }"
                :value="convertDateTimeFromServer($v.accounts.updatedTime.$model)"
                @change="updateInstantField('updatedTime', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-deleted">Deleted</label>
            <input
              type="checkbox"
              class="form-check"
              name="deleted"
              id="accounts-deleted"
              data-cy="deleted"
              :class="{ valid: !$v.accounts.deleted.$invalid, invalid: $v.accounts.deleted.$invalid }"
              v-model="$v.accounts.deleted.$model"
              required
            />
            <div v-if="$v.accounts.deleted.$anyDirty && $v.accounts.deleted.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.deleted.required"> This field is required. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-categories">Categories</label>
            <input
              type="text"
              class="form-control"
              name="categories"
              id="accounts-categories"
              data-cy="categories"
              :class="{ valid: !$v.accounts.categories.$invalid, invalid: $v.accounts.categories.$invalid }"
              v-model="$v.accounts.categories.$model"
              required
            />
            <div v-if="$v.accounts.categories.$anyDirty && $v.accounts.categories.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.categories.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.categories.maxLength">
                This field cannot be longer than 32 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-code">Code</label>
            <input
              type="text"
              class="form-control"
              name="code"
              id="accounts-code"
              data-cy="code"
              :class="{ valid: !$v.accounts.code.$invalid, invalid: $v.accounts.code.$invalid }"
              v-model="$v.accounts.code.$model"
            />
            <div v-if="$v.accounts.code.$anyDirty && $v.accounts.code.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.code.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-openId">Open Id</label>
            <input
              type="text"
              class="form-control"
              name="openId"
              id="accounts-openId"
              data-cy="openId"
              :class="{ valid: !$v.accounts.openId.$invalid, invalid: $v.accounts.openId.$invalid }"
              v-model="$v.accounts.openId.$model"
              required
            />
            <div v-if="$v.accounts.openId.$anyDirty && $v.accounts.openId.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.openId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.openId.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-accessToken">Access Token</label>
            <input
              type="text"
              class="form-control"
              name="accessToken"
              id="accounts-accessToken"
              data-cy="accessToken"
              :class="{ valid: !$v.accounts.accessToken.$invalid, invalid: $v.accounts.accessToken.$invalid }"
              v-model="$v.accounts.accessToken.$model"
              required
            />
            <div v-if="$v.accounts.accessToken.$anyDirty && $v.accounts.accessToken.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.accessToken.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.accessToken.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="accounts-userid">Userid</label>
            <input
              type="number"
              class="form-control"
              name="userid"
              id="accounts-userid"
              data-cy="userid"
              :class="{ valid: !$v.accounts.userid.$invalid, invalid: $v.accounts.userid.$invalid }"
              v-model.number="$v.accounts.userid.$model"
              required
            />
            <div v-if="$v.accounts.userid.$anyDirty && $v.accounts.userid.$invalid">
              <small class="form-text text-danger" v-if="!$v.accounts.userid.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.accounts.userid.numeric"> This field should be a number. </small>
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
            :disabled="$v.accounts.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./accounts-update.component.ts"></script>
