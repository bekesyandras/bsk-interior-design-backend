package hu.progmasters.bskinteriordesignbackend.about.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "anAboutResponse", toBuilder = true, setterPrefix = "with")
@Schema(name = "AboutResponseDto", description = "DTO representing the About section content")
public class AboutResponseDto {

    @Schema(description = "Introductory text displayed on the frontend")
    private String content;


}
