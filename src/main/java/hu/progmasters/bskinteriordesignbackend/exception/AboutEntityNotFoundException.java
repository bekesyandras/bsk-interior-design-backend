package hu.progmasters.bskinteriordesignbackend.exception;

public class AboutEntityNotFoundException extends RuntimeException {
    public AboutEntityNotFoundException(Long id) {
        super("AboutEntity not found with ID: " + id);
    }
}
