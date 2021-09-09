package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 角色-权限中间表\n@author
 */
@ApiModel(description = "@note authority - 角色-权限中间表\n@author")
@Entity
@Table(name = "role_authority")
public class RoleAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    @NotNull
    @ApiModelProperty(value = "角色id", required = true)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    /**
     * 权限id
     */
    @NotNull
    @ApiModelProperty(value = "权限id", required = true)
    @Column(name = "authority_id", nullable = false)
    private Long authorityId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleAuthority id(Long id) {
        this.id = id;
        return this;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public RoleAuthority roleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return this.authorityId;
    }

    public RoleAuthority authorityId(Long authorityId) {
        this.authorityId = authorityId;
        return this;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleAuthority)) {
            return false;
        }
        return id != null && id.equals(((RoleAuthority) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleAuthority{" +
            "id=" + getId() +
            ", roleId=" + getRoleId() +
            ", authorityId=" + getAuthorityId() +
            "}";
    }
}
