package com.hzy.mydemo.modules.basic.repository;

import com.hzy.mydemo.modules.basic.domain.RoleAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoleAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority, Long> {}
