package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 角色表\n@author
 */
@ApiModel(description = "@note authority - 角色表\n@author")
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "创建人", required = true)
    @Column(name = "created_by", length = 64, nullable = false)
    private String createdBy;

    /**
     * 创建时间
     */
    @NotNull
    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "created_time", nullable = false)
    private Instant createdTime;

    /**
     * 更新人
     */
    @Size(max = 64)
    @ApiModelProperty(value = "更新人")
    @Column(name = "updated_by", length = 64)
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "updated_time")
    private Instant updatedTime;

    /**
     * 是否逻辑删除
     */
    @NotNull
    @ApiModelProperty(value = "是否逻辑删除", required = true)
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /**
     * 角色名称
     */
    @NotNull
    @Size(max = 128)
    @ApiModelProperty(value = "角色名称", required = true)
    @Column(name = "role_name", length = 128, nullable = false)
    private String roleName;

    /**
     * 角色编码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "角色编码", required = true)
    @Column(name = "role_code", length = 64, nullable = false)
    private String roleCode;

    /**
     * 角色状态 正常，停用
     */
    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "角色状态 正常，停用", required = true)
    @Column(name = "status", length = 32, nullable = false)
    private String status;

    /**
     * 是否为内置角色
     */
    @NotNull
    @ApiModelProperty(value = "是否为内置角色", required = true)
    @Column(name = "inlay", nullable = false)
    private Boolean inlay;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role id(Long id) {
        this.id = id;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Role createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Role createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Role updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedTime() {
        return this.updatedTime;
    }

    public Role updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public Role deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Role roleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return this.roleCode;
    }

    public Role roleCode(String roleCode) {
        this.roleCode = roleCode;
        return this;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getStatus() {
        return this.status;
    }

    public Role status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getInlay() {
        return this.inlay;
    }

    public Role inlay(Boolean inlay) {
        this.inlay = inlay;
        return this;
    }

    public void setInlay(Boolean inlay) {
        this.inlay = inlay;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return id != null && id.equals(((Role) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", roleName='" + getRoleName() + "'" +
            ", roleCode='" + getRoleCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", inlay='" + getInlay() + "'" +
            "}";
    }
}
