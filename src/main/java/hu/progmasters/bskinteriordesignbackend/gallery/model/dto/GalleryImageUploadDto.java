package hu.progmasters.bskinteriordesignbackend.gallery.model.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "anUploadCommand", toBuilder = true, setterPrefix = "with")
@Schema(name = "GalleryImageUploadCommand", description = "Payload for uploading a gallery image")
public class GalleryImageUploadDto {

    @Schema(type = "string", format = "binary", description = "Image file to upload", required = true)
    private MultipartFile file;

    @Schema(description = "Optional description of the image", example = "Minimalist living room with light wood furniture")
    private String description;



}


