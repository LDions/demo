<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="demoApp.sysAuthority.home.createOrEditLabel" data-cy="SysAuthorityCreateUpdateHeading">Create or edit a SysAuthority</h2>
        <div>
          <div class="form-group" v-if="sysAuthority.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sysAuthority.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sys-authority-authorityName">Authority Name</label>
            <input
              type="text"
              class="form-control"
              name="authorityName"
              id="sys-authority-authorityName"
              data-cy="authorityName"
              :class="{ valid: !$v.sysAuthority.authorityName.$invalid, invalid: $v.sysAuthority.authorityName.$invalid }"
              v-model="$v.sysAuthority.authorityName.$model"
              required
            />
            <div v-if="$v.sysAuthority.authorityName.$anyDirty && $v.sysAuthority.authorityName.$invalid">
              <small class="form-text text-danger" v-if="!$v.sysAuthority.authorityName.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.sysAuthority.authorityName.maxLength">
                This field cannot be longer than 128 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sys-authority-authorityKey">Authority Key</label>
            <input
              type="text"
              class="form-control"
              name="authorityKey"
              id="sys-authority-authorityKey"
              data-cy="authorityKey"
              :class="{ valid: !$v.sysAuthority.authorityKey.$invalid, invalid: $v.sysAuthority.authorityKey.$invalid }"
              v-model="$v.sysAuthority.authorityKey.$model"
            />
            <div v-if="$v.sysAuthority.authorityKey.$anyDirty && $v.sysAuthority.authorityKey.$invalid">
              <small class="form-text text-danger" v-if="!$v.sysAuthority.authorityKey.maxLength">
                This field cannot be longer than 64 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sys-authority-parentId">Parent Id</label>
            <input
              type="number"
              class="form-control"
              name="parentId"
              id="sys-authority-parentId"
              data-cy="parentId"
              :class="{ valid: !$v.sysAuthority.parentId.$invalid, invalid: $v.sysAuthority.parentId.$invalid }"
              v-model.number="$v.sysAuthority.parentId.$model"
              required
            />
            <div v-if="$v.sysAuthority.parentId.$anyDirty && $v.sysAuthority.parentId.$invalid">
              <small class="form-text text-danger" v-if="!$v.sysAuthority.parentId.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.sysAuthority.parentId.numeric"> This field should be a number. </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sys-authority-authorityType">Authority Type</label>
            <input
              type="text"
              class="form-control"
              name="authorityType"
              id="sys-authority-authorityType"
              data-cy="authorityType"
              :class="{ valid: !$v.sysAuthority.authorityType.$invalid, invalid: $v.sysAuthority.authorityType.$invalid }"
              v-model="$v.sysAuthority.authorityType.$model"
              required
            />
            <div v-if="$v.sysAuthority.authorityType.$anyDirty && $v.sysAuthority.authorityType.$invalid">
              <small class="form-text text-danger" v-if="!$v.sysAuthority.authorityType.required"> This field is required. </small>
              <small class="form-text text-danger" v-if="!$v.sysAuthority.authorityType.maxLength">
                This field cannot be longer than 32 characters.
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
            :disabled="$v.sysAuthority.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./sys-authority-update.component.ts"></script>
