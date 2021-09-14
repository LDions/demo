package com.hzy.mydemo.modules.basic.repository;

import com.hzy.mydemo.modules.basic.domain.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdAndState(Long userId, String state);
}
