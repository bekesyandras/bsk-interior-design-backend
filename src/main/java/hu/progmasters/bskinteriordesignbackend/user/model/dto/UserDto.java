package hu.progmasters.bskinteriordesignbackend.user.model.dto;

import hu.progmasters.bskinteriordesignbackend.user.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "aUserDto", toBuilder = true, setterPrefix = "with")
@Schema(description = "User data")
public class UserDto {

    @Schema(description = "User identifier", example = "1")
    private Long id;

    @Schema(description = "Username", example = "admin")
    private String username;

    @Schema(description = "User role", example = "ROLE_ADMIN")
    private Role role;


}


