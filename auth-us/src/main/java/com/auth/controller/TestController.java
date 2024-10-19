/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nicoc
 */
@RestController
public class TestController {

    @GetMapping("/hello-1")
    @PreAuthorize("hasRole('admin_client_role')")
    public String helloAdmin() {
        return "Hello Sprig Boot With Keycloak with ADMIN";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('estudiante_client_role') or hasRole('admin_client_role')")
    public String helloUser() {
        return "Hello Sprig Boot With Keycloak with USER";
    }
}
