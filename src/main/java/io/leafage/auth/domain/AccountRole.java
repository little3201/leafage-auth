/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model class for User Role
 *
 * @author liwenqiang 2021-12-21 17:44
 */
@Entity
@Table(name = "account_role")
public class AccountRole {

    /**
     * 用户主键
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * 组主键
     */
    @Column(name = "role_id")
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
