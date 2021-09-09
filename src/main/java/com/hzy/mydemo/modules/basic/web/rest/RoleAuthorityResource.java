package com.hzy.mydemo.modules.basic.web.rest;

import com.hzy.mydemo.modules.basic.domain.RoleAuthority;
import com.hzy.mydemo.modules.basic.repository.RoleAuthorityRepository;
import com.hzy.mydemo.modules.firstversion.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
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
