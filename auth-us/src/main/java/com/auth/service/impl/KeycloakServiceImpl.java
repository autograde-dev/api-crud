/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.auth.service.impl;

import com.auth.dto.UserDto;
import com.auth.util.KeycloakProvider;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;
import com.auth.service.IKeycloakService;

/**
 *
 * @author nicoc
 */
@Service
@Slf4j
public class KeycloakServiceImpl implements IKeycloakService {

    /**
     * Metodo para listar todos los usuarios de Keycloak
     *
     * @return List<UserRepresentation>
     */
    @Override
    public List<UserRepresentation> findAllUsers() {
        return KeycloakProvider.getRealmResource()
                .users()
                .list();
    }

    /**
     * Metodo para buscar un usuario por su username
     *
     * @return List<UserRepresentation>
     */
    @Override
    public List<UserRepresentation> searchUserByUsername(String username) {
        return KeycloakProvider.getRealmResource()
                .users()
                .searchByUsername(username, true);
    }

    /**
     * Metodo para crear un usuario en keycloak
     *
     * @param userDTO
     * @return String
     */
    @Override
    public String createUser(@NonNull UserDto userDTO) {

        int status = 0;
        UsersResource usersResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        switch (status) {
            case 201 -> {
                String path = response.getLocation().getPath();
                String userId = path.substring(path.lastIndexOf("/") + 1);
                
                CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
                credentialRepresentation.setTemporary(false);
                credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
                credentialRepresentation.setValue(userDTO.getPassword());
                
                usersResource.get(userId).resetPassword(credentialRepresentation);
                
                RealmResource realmResource = KeycloakProvider.getRealmResource();
                
                List<RoleRepresentation> rolesRepresentation = null;
                
                if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
                    rolesRepresentation = List.of(realmResource.roles().get("estudiante").toRepresentation());
                } else {
                    rolesRepresentation = realmResource.roles()
                            .list()
                            .stream()
                            .filter(role -> userDTO.getRoles()
                                    .stream()
                                    .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                            .toList();
                }
                
                realmResource.users().get(userId).roles().realmLevel().add(rolesRepresentation);
                
                return "User created successfully!!";
            }
            case 409 -> {
                log.error("User exist already!");
                return "User exist already!";
            }
            default -> {
                    log.error("Error creating user, please contact with the administrator.");
                    return "Error creating user, please contact with the administrator.";
            }
        }
    }

    /**
     * @param userId
     * Metodo para borrar un usuario en keycloak
     *
     * @return void
     */
    @Override
    public void deleteUser(String userId) {
        KeycloakProvider.getUserResource()
                .get(userId)
                .remove();
    }

    /**
     * Metodo para actualizar un usuario en keycloak
     *
     * @param userId
     * @param userDTO
     * @return void
     */
    @Override
    public void updateUser(String userId, @NonNull UserDto userDTO) {

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource usersResource = KeycloakProvider.getUserResource().get(userId);
        usersResource.update(user);
    }

}
