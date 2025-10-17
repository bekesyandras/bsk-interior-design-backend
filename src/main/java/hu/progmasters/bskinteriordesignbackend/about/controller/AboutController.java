package hu.progmasters.bskinteriordesignbackend.about.controller;

import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutUpdateDto;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutResponseDto;
import hu.progmasters.bskinteriordesignbackend.about.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Retrieves the About section displayed on the frontend")
    public ResponseEntity<AboutResponseDto> getAbout() {
        log.info(CYAN + "HTTP GET About section" + ANSI_RESET);
        AboutResponseDto responseDto = aboutService.getContentSection();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Updates the About section")
    public ResponseEntity<Void> updateAbout(@ModelAttribute AboutUpdateDto command) {
        log.info(CYAN + "HTTP PUT update About section" + ANSI_RESET);
        aboutService.updateContentSection(command);
        return ResponseEntity.ok().build();
    }
}
