package hu.progmasters.bskinteriordesignbackend.exception;

public class GalleryImageNotFoundException extends RuntimeException {

    public GalleryImageNotFoundException(Long id) {
        super("Gallery image not found with ID: " + id);
    }
}
