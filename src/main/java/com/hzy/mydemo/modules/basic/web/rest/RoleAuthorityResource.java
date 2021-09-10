package com.hzy.mydemo.modules.basic.web.rest;

import com.hzy.mydemo.modules.basic.domain.RoleAuthority;
import com.hzy.mydemo.modules.basic.repository.RoleAuthorityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class RoleAuthorityResource {

    private final RoleAuthorityRepository roleAuthorityRepository;

    public RoleAuthorityResource(RoleAuthorityRepository roleAuthorityRepository) {
        this.roleAuthorityRepository = roleAuthorityRepository;
    }

    @GetMapping("/role-authorities")
    public List<RoleAuthority> getAllRoleAuthorities() {
        return roleAuthorityRepository.findAll();
    }

    @GetMapping("/role-authorities/{id}")
    public ResponseEntity<RoleAuthority> getRoleAuthority(@PathVariable Long id) {
        Optional<RoleAuthority> roleAuthority = roleAuthorityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(roleAuthority);
    }
}
