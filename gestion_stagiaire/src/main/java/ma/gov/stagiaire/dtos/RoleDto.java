package ma.gov.stagiaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    
    @NotBlank
    private String name;
    
    private String description;
} 