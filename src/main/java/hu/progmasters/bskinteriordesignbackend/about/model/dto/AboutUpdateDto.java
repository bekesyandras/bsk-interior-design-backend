package hu.progmasters.bskinteriordesignbackend.about.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "anUpdateCommand", toBuilder = true, setterPrefix = "with")
@Schema(description = "Command object for updating the About section content")
public class AboutUpdateDto {

    @Schema(description = "Unique identifier of the About section entry", example = "1")
    private Long id;

    @Size(max = 5000)
    @Schema(description = "New content for the About section", example = "Welcome to our interior design studio...")
    private String content;

    @Schema(type = "string", format = "binary", description = "Image file to upload")
    private MultipartFile profileImage;

}
