package ma.gov.stagiaire.mappers;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.entities.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto toDto(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setName(roleEntity.getName());
        roleDto.setDescription(roleEntity.getDescription());

        return roleDto;
    }

    @Override
    public RoleEntity toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDto.getId());
        roleEntity.setName(roleDto.getName());
        roleEntity.setDescription(roleDto.getDescription());

        return roleEntity;
    }
} 