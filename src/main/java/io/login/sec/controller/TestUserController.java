package io.login.sec.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {
    @PostMapping("/create-test-user")
    public boolean createTestUser() {
        return true;
    }
}
