package com.hzy.mydemo.modules.basic.web.rest;

import com.hzy.mydemo.modules.basic.domain.SysAuthority;
import com.hzy.mydemo.modules.basic.repository.SysAuthorityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class SysAuthorityResource {

    private final SysAuthorityRepository sysAuthorityRepository;

    public SysAuthorityResource(SysAuthorityRepository sysAuthorityRepository) {
        this.sysAuthorityRepository = sysAuthorityRepository;
    }

    @GetMapping("/sys-authorities")
    public List<SysAuthority> getAllSysAuthorities() {
        return sysAuthorityRepository.findAll();
    }

    @GetMapping("/sys-authorities/{id}")
    public ResponseEntity<SysAuthority> getSysAuthority(@PathVariable Long id) {
        Optional<SysAuthority> sysAuthority = sysAuthorityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysAuthority);
    }
}
