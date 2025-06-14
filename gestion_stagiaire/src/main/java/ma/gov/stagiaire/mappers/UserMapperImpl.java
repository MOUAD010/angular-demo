package ma.gov.stagiaire.mappers;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.dtos.UserDto;
import ma.gov.stagiaire.entities.RoleEntity;
import ma.gov.stagiaire.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;

    public UserMapperImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setLogin(userEntity.getLogin());
        userDto.setPassword(userEntity.getPassword());
        
        if (userEntity.getRoles() != null) {
            Set<RoleDto> roles = userEntity.getRoles().stream()
                    .map(roleMapper::toDto)
                    .collect(Collectors.toSet());
            userDto.setRoles(roles);
        }

        return userDto;
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setLogin(userDto.getLogin());
        userEntity.setPassword(userDto.getPassword());
        
        if (userDto.getRoles() != null) {
            Set<RoleEntity> roles = userDto.getRoles().stream()
                    .map(roleMapper::toEntity)
                    .collect(Collectors.toSet());
            userEntity.setRoles(roles);
        }

        return userEntity;
    }
} 