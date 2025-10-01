package hu.progmasters.bskinteriordesignbackend.user.model.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User roles used for authorization")
public enum Role {

    @Schema(description = "Administrator with full access")
    ROLE_ADMIN
}


