package io.login.sec.application;

import io.login.sec.domain.Account;
import io.login.sec.domain.AccountRepository;
import io.login.sec.domain.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException("account not found");
        }

        return UserDetailsImpl
                .builder()
                .id(account.getId())
                .email(account.getEmail())
                .enabled(account.getEnabled())
                .credentialsExpiredAt(account.getCredentialsExpiredAt())
                .expiredAt(account.getExpiredAt())
                .lockedAt(account.getLockedAt())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
