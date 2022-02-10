/*
 * Copyright (c) 2021. Leafage All Right Reserved.
 */
package io.leafage.auth.repository;

import io.leafage.auth.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * role repository
 *
 * @author liwenqiang 2021-12-21 17:44
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
