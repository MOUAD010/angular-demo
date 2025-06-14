package ma.gov.stagiaire.services;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.mappers.RoleMapper;
import ma.gov.stagiaire.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {
    
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    
    @Override
    public RoleDto addRole(RoleDto roleDto) {
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }
    
    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
    }
    
    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
    
    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<RoleDto> getRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toDto);
    }
    
    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }
} 