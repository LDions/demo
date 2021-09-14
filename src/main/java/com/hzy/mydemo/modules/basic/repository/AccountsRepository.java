package com.hzy.mydemo.modules.basic.repository;

import com.hzy.mydemo.modules.basic.domain.Accounts;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByOpenIdAndCategoriesAndDeletedIsFalse(String openId, String category);
}
