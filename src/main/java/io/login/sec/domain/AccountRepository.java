package io.login.sec.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(@NonNull String email);
}
