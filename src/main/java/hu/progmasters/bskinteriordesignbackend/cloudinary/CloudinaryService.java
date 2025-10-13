package hu.progmasters.bskinteriordesignbackend.cloudinary;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import hu.progmasters.bskinteriordesignbackend.cloudinary.model.dto.CloudinaryUploadResultDto;
import hu.progmasters.bskinteriordesignbackend.exception.CloudinaryDeleteException;
import hu.progmasters.bskinteriordesignbackend.exception.CloudinaryUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public CloudinaryUploadResultDto uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();

            return new CloudinaryUploadResultDto(imageUrl, publicId);
        } catch (IOException e) {
            throw new CloudinaryUploadException("Failed to upload image to Cloudinary", e);
        }
    }

    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new CloudinaryDeleteException("Failed to delete image from Cloudinary", e);
        }
    }
}



