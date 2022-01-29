/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.leafage.auth.repository;

import io.leafage.leafage.auth.domain.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 用户角色repository
 *
 * @author liwenqiang 2018/12/17 19:37
 **/
@Repository
public interface UserRoleRepository extends JpaRepository<AccountRole, Long> {

    /**
     * 根据用户ID查询
     *
     * @param userId 用户主键
     * @return 集合
     */
    List<AccountRole> findByUserId(Long userId);

    /**
     * 根据角色ID查询
     *
     * @param roleId 角色主键
     * @return 关联数据集
     */
    List<AccountRole> findByRoleId(Long roleId);

    /**
     * 根据用户ID删除
     *
     * @param userId 用户主键
     */
    void deleteByUserId(Long userId);
}
