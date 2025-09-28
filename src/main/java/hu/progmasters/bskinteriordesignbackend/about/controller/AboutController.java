package hu.progmasters.bskinteriordesignbackend.about.controller;

import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutResponseDto;
import hu.progmasters.bskinteriordesignbackend.about.service.AboutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
