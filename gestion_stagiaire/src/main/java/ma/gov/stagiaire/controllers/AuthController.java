package ma.gov.stagiaire.controllers;

import ma.gov.stagiaire.dtos.RoleDto;
import ma.gov.stagiaire.dtos.UserDto;
import ma.gov.stagiaire.dtos.auth.JwtResponse;
import ma.gov.stagiaire.dtos.auth.LoginRequest;
import ma.gov.stagiaire.dtos.auth.MessageResponse;
import ma.gov.stagiaire.dtos.auth.SignupRequest;
import ma.gov.stagiaire.entities.RoleEntity;
import ma.gov.stagiaire.entities.UserEntity;
import ma.gov.stagiaire.mappers.UserMapper;
import ma.gov.stagiaire.security.jwt.JwtUtils;
import ma.gov.stagiaire.services.IRoleService;
import ma.gov.stagiaire.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    
    public AuthController(AuthenticationManager authenticationManager, IUserService userService,
                         IRoleService roleService, PasswordEncoder encoder, JwtUtils jwtUtils,
                         UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userMapper = userMapper;
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(new JwtResponse(jwt,
                                                userDetails.getId(),
                                                userDetails.getUsername(),
                                                userDetails.getName(),
                                                roles));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Check if username already exists
        if (userService.existsByLogin(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }
        
        // Create new user account
        UserDto userDto = new UserDto();
        userDto.setName(signUpRequest.getName());
        userDto.setLogin(signUpRequest.getUsername());
        userDto.setPassword(encoder.encode(signUpRequest.getPassword()));
        
        Set<RoleDto> roles = new HashSet<>();
        
        if (signUpRequest.getRoles() == null || signUpRequest.getRoles().isEmpty()) {
            RoleDto userRole = roleService.getRoleByName("USER")
                .orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
            roles.add(userRole);
        } else {
            signUpRequest.getRoles().forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleDto adminRole = roleService.getRoleByName("ADMIN")
                            .orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        RoleDto modRole = roleService.getRoleByName("MODERATOR")
                            .orElseThrow(() -> new RuntimeException("Error: Role MODERATOR is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        RoleDto userRole = roleService.getRoleByName("USER")
                            .orElseThrow(() -> new RuntimeException("Error: Role USER is not found."));
                        roles.add(userRole);
                }
            });
        }
        
        userDto.setRoles(roles);
        userService.addUser(userDto);
        
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
} 