package ma.gov.stagiaire.services;

import ma.gov.stagiaire.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDto addUser(UserDto userDto);
    UserDto updateUser(UserDto userDto);
    void deleteUser(Long id);
    List<UserDto> selectUsers();
    Optional<UserDto> getUserByLogin(String login);
    boolean existsByLogin(String login);
}
