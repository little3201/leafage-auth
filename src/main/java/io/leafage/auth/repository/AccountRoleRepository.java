/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.auth.repository;

import io.leafage.auth.domain.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * account role repository
 *
 * @author liwenqiang 2021-12-21 17:44
 **/
@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {

    /**
     * 根据账号主键查询
     *
     * @param accountId 账号主键
     * @return 集合
     */
    List<AccountRole> findByAccountId(Long accountId);
}
