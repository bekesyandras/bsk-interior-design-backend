package hu.progmasters.bskinteriordesignbackend.about.controller;

import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutContentUpdateDto;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutResponseDto;
import hu.progmasters.bskinteriordesignbackend.about.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/about")
@Tag(name = "About", description = "Endpoints related to the About section")
@RequiredArgsConstructor
@Slf4j
public class AboutController {

    public static final String CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final AboutService aboutService;


    @GetMapping
    @Operation(summary = "Retrieves the About section content displayed on the frontend")
    public ResponseEntity<AboutResponseDto> getAboutContent() {
        log.info(CYAN + "HTTP GET content for about section" + ANSI_RESET);
        AboutResponseDto responseDto = aboutService.getContent();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update content of a specific About section entry")
    public ResponseEntity<Void> updateAboutContent(@Valid @RequestBody AboutContentUpdateDto command) {
        log.info(CYAN + "HTTP PUT update about content" + ANSI_RESET);
        aboutService.updateContent(command);
        return ResponseEntity.ok().build();
    }
}
