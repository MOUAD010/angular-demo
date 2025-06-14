package ma.gov.stagiaire.mappers;


import ma.gov.stagiaire.dtos.UserDto;
import ma.gov.stagiaire.entities.UserEntity;

public interface UserMapper {

    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);

}
