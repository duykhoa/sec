package io.login.sec.controller;

import io.login.sec.annotations.IsDevEnvCondition;
import io.login.sec.application.SetupTestDataUsecase;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Conditional(IsDevEnvCondition.class)
@RestController
@RequestMapping("/demo")
public class DevAuthenticationController {
    private final SetupTestDataUsecase setupTestDataUsecase;

    public DevAuthenticationController(SetupTestDataUsecase setupTestDataUsecase) {
        this.setupTestDataUsecase = setupTestDataUsecase;
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminDashboard() {
        return "Successfully access admin dashboard";
    }

    @GetMapping("/user/dashboard")
    @PreAuthorize("hasRole('USER')")
    public String showUserDashboard() {
        return "Successfully access User dashboard";
    }

    @GetMapping("/superadmin/dashboard")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String showSuperAdminDashboard() {
        return "Successfully access Super Admin dashboard";
    }

    @GetMapping("/default")
    public String showDefaultPage() {
        return "Without any authority, guest can see this page";
    }

    @PostMapping("/superadmin/run-me-first")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String runMeFirst() {
        return setupTestDataUsecase.execute();
    }
}
