package com.hzy.mydemo.modules.sys.repository;


import com.hzy.mydemo.modules.sys.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
