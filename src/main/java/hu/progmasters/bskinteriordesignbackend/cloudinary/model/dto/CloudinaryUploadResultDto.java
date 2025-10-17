package hu.progmasters.bskinteriordesignbackend.cloudinary.model.dto;

public class CloudinaryUploadResultDto {
    private final String imageUrl;
    private final String publicId;

    public CloudinaryUploadResultDto(String imageUrl, String publicId) {
        this.imageUrl = imageUrl;
        this.publicId = publicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublicId() {
        return publicId;
    }
}
