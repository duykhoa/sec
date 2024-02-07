package io.login.sec.application;

import io.login.sec.domain.Account;
import io.login.sec.domain.AccountRepository;
import io.login.sec.domain.Role;
import io.login.sec.domain.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .createAuthorityList(account.getRoles().stream().map(Role::getName).toList());

        UserDetailsImpl userDetails = UserDetailsImpl
                .builder()
                .id(account.getId())
                .email(account.getEmail())
                .password(account.getEncodedPassword())
                .enabled(account.getEnabled())
                .credentialsExpiredAt(account.getCredentialsExpiredAt())
                .expiredAt(account.getExpiredAt())
                .lockedAt(account.getLockedAt())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .authorityList(grantedAuthorities)
                .build();
        return userDetails;
    }
}
