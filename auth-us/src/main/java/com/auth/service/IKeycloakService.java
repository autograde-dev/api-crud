/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.service;

import com.auth.dto.UserDto;
import java.util.List;
import org.keycloak.representations.idm.UserRepresentation;

/**
 *
 * @author nicoc
 */
public interface IKeycloakService {

    List<UserRepresentation> findAllUsers();

    List<UserRepresentation> searchUserByUsername(String username);

    String createUser(UserDto userDTO);

    void deleteUser(String userId);

    void updateUser(String userId, UserDto userDTO);
}
