package com.hzy.mydemo.modules.verification.repository;

import com.hzy.mydemo.modules.verification.domain.VerificationCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findFirstByPhone(String phone);
}
