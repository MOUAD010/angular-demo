package ma.gov.stagiaire.services;

import ma.gov.stagiaire.dtos.RoleDto;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    RoleDto addRole(RoleDto roleDto);
    RoleDto updateRole(RoleDto roleDto);
    void deleteRole(Long id);
    List<RoleDto> getAllRoles();
    Optional<RoleDto> getRoleByName(String name);
    boolean existsByName(String name);
} 