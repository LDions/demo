package com.hzy.mydemo.modules.basic.web.rest;

import com.hzy.mydemo.modules.basic.domain.SysAuthority;
import com.hzy.mydemo.modules.basic.repository.SysAuthorityRepository;
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

/**
 * REST controller for managing {@link com.hzy.mydemo.modules.basic.domain.SysAuthority}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SysAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(SysAuthorityResource.class);

    private static final String ENTITY_NAME = "sysAuthority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SysAuthorityRepository sysAuthorityRepository;

    public SysAuthorityResource(SysAuthorityRepository sysAuthorityRepository) {
        this.sysAuthorityRepository = sysAuthorityRepository;
    }

    /**
     * {@code POST  /sys-authorities} : Create a new sysAuthority.
     *
     * @param sysAuthority the sysAuthority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sysAuthority, or with status {@code 400 (Bad Request)} if the sysAuthority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sys-authorities")
    public ResponseEntity<SysAuthority> createSysAuthority(@Valid @RequestBody SysAuthority sysAuthority) throws URISyntaxException {
        log.debug("REST request to save SysAuthority : {}", sysAuthority);
        if (sysAuthority.getId() != null) {
            throw new BadRequestAlertException("A new sysAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysAuthority result = sysAuthorityRepository.save(sysAuthority);
        return ResponseEntity
            .created(new URI("/api/sys-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sys-authorities/:id} : Updates an existing sysAuthority.
     *
     * @param id the id of the sysAuthority to save.
     * @param sysAuthority the sysAuthority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysAuthority,
     * or with status {@code 400 (Bad Request)} if the sysAuthority is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sysAuthority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sys-authorities/{id}")
    public ResponseEntity<SysAuthority> updateSysAuthority(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SysAuthority sysAuthority
    ) throws URISyntaxException {
        log.debug("REST request to update SysAuthority : {}, {}", id, sysAuthority);
        if (sysAuthority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysAuthority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysAuthorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SysAuthority result = sysAuthorityRepository.save(sysAuthority);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysAuthority.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sys-authorities/:id} : Partial updates given fields of an existing sysAuthority, field will ignore if it is null
     *
     * @param id the id of the sysAuthority to save.
     * @param sysAuthority the sysAuthority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sysAuthority,
     * or with status {@code 400 (Bad Request)} if the sysAuthority is not valid,
     * or with status {@code 404 (Not Found)} if the sysAuthority is not found,
     * or with status {@code 500 (Internal Server Error)} if the sysAuthority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sys-authorities/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SysAuthority> partialUpdateSysAuthority(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SysAuthority sysAuthority
    ) throws URISyntaxException {
        log.debug("REST request to partial update SysAuthority partially : {}, {}", id, sysAuthority);
        if (sysAuthority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, sysAuthority.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!sysAuthorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SysAuthority> result = sysAuthorityRepository
            .findById(sysAuthority.getId())
            .map(
                existingSysAuthority -> {
                    if (sysAuthority.getAuthorityName() != null) {
                        existingSysAuthority.setAuthorityName(sysAuthority.getAuthorityName());
                    }
                    if (sysAuthority.getAuthorityKey() != null) {
                        existingSysAuthority.setAuthorityKey(sysAuthority.getAuthorityKey());
                    }
                    if (sysAuthority.getParentId() != null) {
                        existingSysAuthority.setParentId(sysAuthority.getParentId());
                    }
                    if (sysAuthority.getAuthorityType() != null) {
                        existingSysAuthority.setAuthorityType(sysAuthority.getAuthorityType());
                    }

                    return existingSysAuthority;
                }
            )
            .map(sysAuthorityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sysAuthority.getId().toString())
        );
    }

    /**
     * {@code GET  /sys-authorities} : get all the sysAuthorities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sysAuthorities in body.
     */
    @GetMapping("/sys-authorities")
    public List<SysAuthority> getAllSysAuthorities() {
        log.debug("REST request to get all SysAuthorities");
        return sysAuthorityRepository.findAll();
    }

    /**
     * {@code GET  /sys-authorities/:id} : get the "id" sysAuthority.
     *
     * @param id the id of the sysAuthority to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sysAuthority, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sys-authorities/{id}")
    public ResponseEntity<SysAuthority> getSysAuthority(@PathVariable Long id) {
        log.debug("REST request to get SysAuthority : {}", id);
        Optional<SysAuthority> sysAuthority = sysAuthorityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sysAuthority);
    }

    /**
     * {@code DELETE  /sys-authorities/:id} : delete the "id" sysAuthority.
     *
     * @param id the id of the sysAuthority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sys-authorities/{id}")
    public ResponseEntity<Void> deleteSysAuthority(@PathVariable Long id) {
        log.debug("REST request to delete SysAuthority : {}", id);
        sysAuthorityRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
