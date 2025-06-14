package ma.gov.stagiaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String login;
    
    private String password;
    
    private Set<RoleDto> roles = new HashSet<>();
}
