package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 账号表\n@author
 */
@ApiModel(description = "@note authority - 账号表\n@author")
@Entity
@Table(name = "accounts")
public class Accounts implements Serializable {

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
     * 账号类别 登录账号，手机号，微信，QQ
     */
    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "账号类别 登录账号，手机号，微信，QQ", required = true)
    @Column(name = "categories", length = 32, nullable = false)
    private String categories;

    /**
     * 账号
     */
    @Size(max = 64)
    @ApiModelProperty(value = "账号")
    @Column(name = "code", length = 64)
    private String code;

    /**
     * openid
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "openid", required = true)
    @Column(name = "open_id", length = 64, nullable = false)
    private String openId;

    /**
     * token
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "token", required = true)
    @Column(name = "access_token", length = 64, nullable = false)
    private String accessToken;

    /**
     * 用户ID
     */
    @NotNull
    @ApiModelProperty(value = "用户ID", required = true)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accounts id(Long id) {
        this.id = id;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Accounts createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Accounts createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Accounts updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedTime() {
        return this.updatedTime;
    }

    public Accounts updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public Accounts deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCategories() {
        return this.categories;
    }

    public Accounts categories(String categories) {
        this.categories = categories;
        return this;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCode() {
        return this.code;
    }

    public Accounts code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return this.openId;
    }

    public Accounts openId(String openId) {
        this.openId = openId;
        return this;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public Accounts accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Accounts userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Accounts)) {
            return false;
        }
        return id != null && id.equals(((Accounts) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Accounts{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", categories='" + getCategories() + "'" +
            ", code='" + getCode() + "'" +
            ", openId='" + getOpenId() + "'" +
            ", accessToken='" + getAccessToken() + "'" +
            ", userid=" + getUserId() +
            "}";
    }
}
