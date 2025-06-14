package ma.gov.stagiaire.controllers;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.services.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleController {
    
    private final IRoleService roleService;
    
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        if (roleService.existsByName(roleDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(roleService.addRole(roleDto));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @Valid @RequestBody RoleDto roleDto) {
        roleDto.setId(id);
        return ResponseEntity.ok(roleService.updateRole(roleDto));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
} 