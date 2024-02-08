package io.login.sec.application;

import io.login.sec.domain.Account;
import io.login.sec.domain.AccountRepository;
import io.login.sec.domain.Role;
import io.login.sec.domain.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SetupTestDataUsecase {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public SetupTestDataUsecase(AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    public String execute() {
        Role userRole = roleRepository.findByNameIgnoreCase("ROLE_USER");
        Role adminRole = roleRepository.findByNameIgnoreCase("ROLE_ADMIN");
        Role superAdminRole = roleRepository.findByNameIgnoreCase("ROLE_SUPER_ADMIN");

//        Creates accounts with role USER, ADMIN

        Account userAccount = Account
                .builder()
                .email("user@email.com")
                .password("password")
                .enabled(true)
                .roles(Set.of(userRole))
                .build();

        Account adminAccount = Account
                .builder()
                .email("admin@email.com")
                .password("password")
                .enabled(true)
                .roles(Set.of(adminRole))
                .build();

        Account multiRolesAccount1 = Account
                .builder()
                .email("multiroles1@email.com")
                .password("password")
                .enabled(true)
                .roles(Set.of(userRole, adminRole))
                .build();

        Account multiRolesAccount2 = Account
                .builder()
                .email("multiroles2@email.com")
                .password("password")
                .enabled(true)
                .roles(Set.of(userRole, superAdminRole))
                .build();

        Account disabledAccount = Account
                .builder()
                .email("disabled@email.com")
                .password("password")
                .enabled(false)
                .roles(Set.of(userRole, superAdminRole))
                .build();

        List<Account> accounts = List.of(
                userAccount,
                adminAccount,
                multiRolesAccount1,
                multiRolesAccount2,
                disabledAccount
        );

        accountRepository.saveAll(accounts);

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the testing users list, the password is 'password' for all account\n");
        accounts.forEach(acc -> sb.append("\t").append(acc.getEmail()).append("\n"));
        return sb.toString();
    }
}
