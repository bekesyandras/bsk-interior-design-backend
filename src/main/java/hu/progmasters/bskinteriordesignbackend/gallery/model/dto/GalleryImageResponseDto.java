package hu.progmasters.bskinteriordesignbackend.gallery.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "aResponseDto", toBuilder = true, setterPrefix = "with")
public class GalleryImageResponseDto {

    @Schema(description = "Unique identifier of the image", example = "42")
    private Long id;

    @Schema(description = "Public URL of the uploaded image", example = "https://res.cloudinary.com/.../image.jpg")
    private String imageUrl;

    @Schema(description = "Optional description of the image", example = "Living room interior with natural light")
    private String description;


}
