package io.login.sec.controller;

import io.login.sec.domain.Account;
import io.login.sec.domain.AccountRepository;
import io.login.sec.domain.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestUserController {

    private final AccountRepository accountRepository;

    public TestUserController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("/create-test-user")
    public boolean createTestUser() {
        Account account = Account
                .builder()
                .email("admin@email.com")
                .password("password")
                .enabled(true)
                .build();

        accountRepository.save(account);
        return true;
    }
}
