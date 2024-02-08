package io.login.sec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("versions")
@PreAuthorize("hasRole('USER')")
public class VersionsController {
    @GetMapping()
    public String get() {
        return "0.0.1";
    }
}
