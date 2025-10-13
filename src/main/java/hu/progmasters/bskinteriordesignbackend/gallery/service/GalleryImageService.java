package hu.progmasters.bskinteriordesignbackend.gallery.service;

import hu.progmasters.bskinteriordesignbackend.cloudinary.CloudinaryService;
import hu.progmasters.bskinteriordesignbackend.cloudinary.model.dto.CloudinaryUploadResultDto;
import hu.progmasters.bskinteriordesignbackend.exception.GalleryImageNotFoundException;
import hu.progmasters.bskinteriordesignbackend.gallery.model.domain.GalleryImageEntity;
import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageResponseDto;
import hu.progmasters.bskinteriordesignbackend.gallery.model.dto.GalleryImageUploadDto;
import hu.progmasters.bskinteriordesignbackend.gallery.repository.GalleryImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;
    private final CloudinaryService cloudinaryService;


    public GalleryImageResponseDto uploadImage(GalleryImageUploadDto command) {
        MultipartFile file = command.getFile();
        String description = command.getDescription();

        int nextOrder = galleryImageRepository.findTopByOrderByDisplayOrderDesc()
                .map(entity -> entity.getDisplayOrder() + 1)
                .orElse(1);

        CloudinaryUploadResultDto result = cloudinaryService.uploadImage(file);


        GalleryImageEntity saved = galleryImageRepository.save(
                GalleryImageEntity.aGalleryImage()
                        .withImageUrl(result.getImageUrl())
                        .withCloudinaryPublicId(result.getPublicId())
                        .withDescription(description)
                        .withDisplayOrder(nextOrder)
                        .build()
        );


        return GalleryImageResponseDto.aResponseDto()
                .withId(saved.getId())
                .withImageUrl(saved.getImageUrl())
                .withDescription(saved.getDescription())
                .build();
    }

    public Page<GalleryImageResponseDto> getPagedImages(Pageable pageable) {
        Sort sort = Sort.by("displayOrder").ascending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        return galleryImageRepository.findAll(sortedPageable)
                .map(image -> GalleryImageResponseDto.aResponseDto()
                        .withId(image.getId())
                        .withImageUrl(image.getImageUrl())
                        .withDescription(image.getDescription())
                        .build());
    }

    public void deleteImage(Long id) {
        GalleryImageEntity image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new GalleryImageNotFoundException(id));

        cloudinaryService.deleteImage(image.getCloudinaryPublicId());

        galleryImageRepository.delete(image);

        List<GalleryImageEntity> images = galleryImageRepository.findAll(Sort.by("displayOrder"));
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setDisplayOrder(i + 1);
        }
        galleryImageRepository.saveAll(images);
    }
}


