/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.dto;

import java.io.Serializable;
import java.util.Set;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 *
 * @author nicoc
 */
@Value
@RequiredArgsConstructor
@Builder
public class UserDto implements Serializable {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<String> roles;
}
