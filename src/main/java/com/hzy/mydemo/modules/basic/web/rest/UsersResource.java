package com.hzy.mydemo.modules.basic.web.rest;

import com.hzy.mydemo.modules.basic.domain.Users;
import com.hzy.mydemo.modules.basic.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/users")
@Transactional
public class UsersResource {

    private final UsersRepository usersRepository;

    public UsersResource(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/list")
    public String getAllUsers() {
        String info = "user information";
        return info;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<Users> getUsers(@PathVariable Long id) {
        Optional<Users> users = usersRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(users);
    }
}
