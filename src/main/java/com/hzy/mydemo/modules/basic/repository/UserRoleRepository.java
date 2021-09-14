package com.hzy.mydemo.modules.basic.repository;

import com.hzy.mydemo.modules.basic.domain.UserRole;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.DoubleStream;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select role.roleCode from UserRole userRole left join Role role on userRole.roleId = role.id where userRole.userId = ?1")
    Set<String> getAllRoleCodeByUserId(Long userId);

    List<UserRole> findAllByUserId(Long userId);
}
