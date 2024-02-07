package io.login.sec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
public class UserDetailsImpl extends Account implements UserDetails {
    private List<GrantedAuthority> authorityList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableList(authorityList);
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return getExpiredAt() == null || getExpiredAt().isBefore(Instant.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return getLockedAt() == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getCredentialsExpiredAt() == null || getCredentialsExpiredAt().isBefore(Instant.now());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
