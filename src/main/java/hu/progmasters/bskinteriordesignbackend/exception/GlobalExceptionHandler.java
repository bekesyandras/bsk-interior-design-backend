package hu.progmasters.bskinteriordesignbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CloudinaryUploadException.class)
    public ResponseEntity<String> handleCloudinaryUploadException(CloudinaryUploadException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Image upload failed: " + ex.getMessage());
    }

    @ExceptionHandler(AboutEntityNotFoundException.class)
    public ResponseEntity<String> handleAboutEntityNotFoundException(AboutEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(GalleryImageNotFoundException.class)
    public ResponseEntity<String> handleGalleryImageNotFoundException(GalleryImageNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}


