package hu.progmasters.bskinteriordesignbackend.gallery.service;

import hu.progmasters.bskinteriordesignbackend.cloudinary.CloudinaryService;
import hu.progmasters.bskinteriordesignbackend.exception.GalleryImageNotFoundException;
import hu.progmasters.bskinteriordesignbackend.gallery.model.domain.GalleryImageEntity;
import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageResponseDto;
import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageUploadDto;
import hu.progmasters.bskinteriordesignbackend.gallery.repository.GalleryImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
@RequiredArgsConstructor
public class GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;
    private final CloudinaryService cloudinaryService;


    public GalleryImageResponseDto uploadImage(GalleryImageUploadDto command) {
        String imageUrl = cloudinaryService.uploadImage(command.getFile());
        GalleryImageEntity saved = galleryImageRepository.save(
                GalleryImageEntity.aGalleryImage()
                        .withImageUrl(imageUrl)
                        .withDescription(command.getDescription())
                        .build()
        );
        return GalleryImageResponseDto.aResponseDto()
                .withId(saved.getId())
                .withImageUrl(saved.getImageUrl())
                .withDescription(saved.getDescription())
                .build();
    }

    public Page<GalleryImageResponseDto> getPagedImages(Pageable pageable) {
        return galleryImageRepository.findAll(pageable)
                .map(image -> GalleryImageResponseDto.aResponseDto()
                        .withId(image.getId())
                        .withImageUrl(image.getImageUrl())
                        .withDescription(image.getDescription())
                        .build());
    }

    public void deleteImage(Long id) {
        GalleryImageEntity image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new GalleryImageNotFoundException(id));
        galleryImageRepository.delete(image);
    }
}


