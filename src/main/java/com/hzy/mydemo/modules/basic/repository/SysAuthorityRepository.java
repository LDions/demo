package com.hzy.mydemo.modules.basic.repository;

import com.hzy.mydemo.modules.basic.domain.SysAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SysAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {}
