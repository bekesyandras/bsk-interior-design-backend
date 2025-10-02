package hu.progmasters.bskinteriordesignbackend.gallery.controller;

import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageResponseDto;
import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageUploadDto;
import hu.progmasters.bskinteriordesignbackend.gallery.service.GalleryImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/gallery")
@Tag(name = "Gallery", description = "Endpoints related to gallery image management")
@RequiredArgsConstructor
@Slf4j
public class GalleryImageController {

    public static final String CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final GalleryImageService galleryImageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Upload a new image to the gallery")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
            @ApiResponse(responseCode = "502", description = "Image upload failed due to Cloudinary error")
    })
    public ResponseEntity<GalleryImageResponseDto> uploadGalleryImage(@ModelAttribute GalleryImageUploadDto command) {
        log.info(CYAN + "HTTP POST upload gallery image" + ANSI_RESET);
        GalleryImageResponseDto response = galleryImageService.uploadImage(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paged")
    @Operation(summary = "Retrieve paginated gallery images")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated list of gallery images")
    })
    public ResponseEntity<Page<GalleryImageResponseDto>> getPagedGalleryImages(Pageable pageable) {
        log.info(CYAN + "HTTP GET paginated gallery images" + ANSI_RESET);
        Page<GalleryImageResponseDto> page = galleryImageService.getPagedImages(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a gallery image by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Image deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Image not found")
    })
    public ResponseEntity<Void> deleteGalleryImage(@PathVariable Long id) {
        log.info(CYAN + "HTTP DELETE gallery image with ID: " + id + ANSI_RESET);
        galleryImageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}


