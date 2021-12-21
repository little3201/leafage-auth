/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.leafage.auth.repository;

import io.leafage.leafage.auth.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 角色信息repository
 *
 * @author liwenqiang 2018/9/26 11:06
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * 根据code查询
     *
     * @param code 唯一标识
     * @return 信息
     */
    Role getByCodeAndEnabledTrue(String code);

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 信息
     */
    Page<Role> findByEnabledTrue(Pageable pageable);

    /**
     * 查询
     *
     * @return 信息
     */
    List<Role> findByEnabledTrue();
}
