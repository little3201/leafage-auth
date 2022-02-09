/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Model class for Role
 *
 * @author liwenqiang 2021-12-21 17:44
 */
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {

    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 上级主键
     */
    private Long superior;
    /**
     * 描述
     */
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSuperior() {
        return superior;
    }

    public void setSuperior(Long superior) {
        this.superior = superior;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
