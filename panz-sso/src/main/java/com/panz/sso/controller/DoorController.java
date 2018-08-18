package com.panz.sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class DoorController {
    @RequestMapping("/door/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}