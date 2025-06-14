package ma.gov.stagiaire.mappers;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.entities.RoleEntity;

public interface RoleMapper {
    RoleDto toDto(RoleEntity roleEntity);
    RoleEntity toEntity(RoleDto roleDto);
} 