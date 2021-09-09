package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 权限表\n@author
 */
@ApiModel(description = "@note authority - 权限表\n@author")
@Entity
@Table(name = "sys_authority")
public class SysAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名称
     */
    @NotNull
    @Size(max = 128)
    @ApiModelProperty(value = "权限名称", required = true)
    @Column(name = "authority_name", length = 128, nullable = false)
    private String authorityName;

    /**
     * 权限键值
     */
    @Size(max = 64)
    @ApiModelProperty(value = "权限键值")
    @Column(name = "authority_key", length = 64)
    private String authorityKey;

    /**
     * 父级id
     */
    @NotNull
    @ApiModelProperty(value = "父级id", required = true)
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    /**
     * 权限类型 默认
     */
    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "权限类型 默认", required = true)
    @Column(name = "authority_type", length = 32, nullable = false)
    private String authorityType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SysAuthority id(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthorityName() {
        return this.authorityName;
    }

    public SysAuthority authorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityKey() {
        return this.authorityKey;
    }

    public SysAuthority authorityKey(String authorityKey) {
        this.authorityKey = authorityKey;
        return this;
    }

    public void setAuthorityKey(String authorityKey) {
        this.authorityKey = authorityKey;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public SysAuthority parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAuthorityType() {
        return this.authorityType;
    }

    public SysAuthority authorityType(String authorityType) {
        this.authorityType = authorityType;
        return this;
    }

    public void setAuthorityType(String authorityType) {
        this.authorityType = authorityType;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysAuthority)) {
            return false;
        }
        return id != null && id.equals(((SysAuthority) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SysAuthority{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", authorityKey='" + getAuthorityKey() + "'" +
            ", parentId=" + getParentId() +
            ", authorityType='" + getAuthorityType() + "'" +
            "}";
    }
}
