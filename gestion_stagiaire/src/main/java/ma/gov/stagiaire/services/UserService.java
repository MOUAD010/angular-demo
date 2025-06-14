package ma.gov.stagiaire.services;


import ma.gov.stagiaire.dtos.UserDto;
import ma.gov.stagiaire.mappers.UserMapper;
import ma.gov.stagiaire.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserDto addUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> selectUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> userMapper.toDto(userEntity))
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<UserDto> getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(userEntity -> userMapper.toDto(userEntity));
    }
    
    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}
