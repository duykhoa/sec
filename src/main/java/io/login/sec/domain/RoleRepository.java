package io.login.sec.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCase(@NonNull String name);
}
