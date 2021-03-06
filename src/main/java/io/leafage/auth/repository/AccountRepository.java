package io.leafage.auth.repository;

import io.leafage.auth.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * account repository
 *
 * @author liwenqiang 2021-12-21 17:44
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * 根据 username 查询
     *
     * @param username 账号
     * @return 查询结果
     */
    Account getByUsername(String username);
}
