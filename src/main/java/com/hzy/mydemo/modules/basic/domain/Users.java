package com.hzy.mydemo.modules.basic.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * @note authority - 用户表\n@author
 */
@ApiModel(description = "@note authority - 用户表\n@author")
@Entity
@Table(name = "users")
public class Users implements Serializable {

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
     * 姓名
     */
    @NotNull
    @Size(max = 128)
    @ApiModelProperty(value = "姓名", required = true)
    @Column(name = "name", length = 128, nullable = false)
    private String name;

    /**
     * 性别
     */
    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "性别", required = true)
    @Column(name = "sex", length = 32, nullable = false)
    private String sex;

    /**
     * 密码
     */
    @NotNull
    @Size(max = 512)
    @ApiModelProperty(value = "密码", required = true)
    @Column(name = "password", length = 512, nullable = false)
    private String password;

    /**
     * 用户状态 0正常，1禁用
     */
    @NotNull
    @Size(max = 32)
    @ApiModelProperty(value = "用户状态 0正常，1禁用", required = true)
    @Column(name = "state", length = 32, nullable = false)
    private String state;

    /**
     * 头像
     */
    @Size(max = 2000)
    @ApiModelProperty(value = "头像")
    @Column(name = "avatar", length = 2000)
    private String avatar;

    /**
     * 重置码
     */
    @Size(max = 64)
    @ApiModelProperty(value = "重置码")
    @Column(name = "reset_code", length = 64)
    private String resetCode;

    /**
     * 重置时间
     */
    @ApiModelProperty(value = "重置时间")
    @Column(name = "reset_time")
    private Instant resetTime;

    /**
     * 用户编码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "用户编码", required = true)
    @Column(name = "user_code", length = 64, nullable = false)
    private String userCode;

    /**
     * 手机号
     */
    @Size(max = 11)
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone", length = 11)
    private String phone;

    /**
     * 激活码
     */
    @Size(max = 64)
    @ApiModelProperty(value = "激活码")
    @Column(name = "activate", length = 64)
    private String activate;

    /**
     * 邮箱
     */
    @Size(max = 320)
    @ApiModelProperty(value = "邮箱")
    @Column(name = "email", length = 320)
    private String email;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users id(Long id) {
        this.id = id;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Users createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return this.createdTime;
    }

    public Users createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Users updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedTime() {
        return this.updatedTime;
    }

    public Users updatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public void setUpdatedTime(Instant updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public Users deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return this.name;
    }

    public Users name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public Users sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return this.password;
    }

    public Users password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return this.state;
    }

    public Users state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Users avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getResetCode() {
        return this.resetCode;
    }

    public Users resetCode(String resetCode) {
        this.resetCode = resetCode;
        return this;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public Instant getResetTime() {
        return this.resetTime;
    }

    public Users resetTime(Instant resetTime) {
        this.resetTime = resetTime;
        return this;
    }

    public void setResetTime(Instant resetTime) {
        this.resetTime = resetTime;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public Users userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPhone() {
        return this.phone;
    }

    public Users phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getActivate() {
        return this.activate;
    }

    public Users activate(String activate) {
        this.activate = activate;
        return this;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }

    public String getEmail() {
        return this.email;
    }

    public Users email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Users)) {
            return false;
        }
        return id != null && id.equals(((Users) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Users{" +
            "id=" + getId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdTime='" + getCreatedTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updatedTime='" + getUpdatedTime() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", name='" + getName() + "'" +
            ", sex='" + getSex() + "'" +
            ", password='" + getPassword() + "'" +
            ", state='" + getState() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", resetCode='" + getResetCode() + "'" +
            ", resetTime='" + getResetTime() + "'" +
            ", userCode='" + getUserCode() + "'" +
            ", phone='" + getPhone() + "'" +
            ", activate='" + getActivate() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
