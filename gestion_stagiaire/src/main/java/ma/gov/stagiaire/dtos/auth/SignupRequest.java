package ma.gov.stagiaire.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    private String name;
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    private Set<String> roles;
} 