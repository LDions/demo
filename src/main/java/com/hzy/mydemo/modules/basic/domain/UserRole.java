package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 用户-角色中间表\n@author
 */
@ApiModel(description = "@note authority - 用户-角色中间表\n@author")
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @NotNull
    @ApiModelProperty(value = "用户id", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 角色id
     */
    @NotNull
    @ApiModelProperty(value = "角色id", required = true)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole id(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public UserRole userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public UserRole roleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRole)) {
            return false;
        }
        return id != null && id.equals(((UserRole) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserRole{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", roleId=" + getRoleId() +
            "}";
    }
}
