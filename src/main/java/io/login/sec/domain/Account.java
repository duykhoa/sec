package io.login.sec.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account", indexes = {
        @Index(name = "idx_account_email", columnList = "email")
})
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    @NotNull
    @Column(name = "encoded_password", nullable = false)
    private String encodedPassword;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "expired_at")
    private Instant expiredAt;

    @Column(name = "locked_at")
    private Instant lockedAt;

    @Column(name = "credentials_expired_at")
    private Instant credentialsExpiredAt;

    @Column(name = "enabled")
    private Boolean enabled;
}
