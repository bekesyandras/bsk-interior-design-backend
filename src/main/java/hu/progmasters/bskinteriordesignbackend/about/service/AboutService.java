package hu.progmasters.bskinteriordesignbackend.about.service;

import hu.progmasters.bskinteriordesignbackend.about.model.domain.AboutEntity;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutUpdateDto;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutResponseDto;
import hu.progmasters.bskinteriordesignbackend.about.repository.AboutRepository;
import hu.progmasters.bskinteriordesignbackend.cloudinary.CloudinaryService;
import hu.progmasters.bskinteriordesignbackend.cloudinary.model.dto.CloudinaryUploadResultDto;
import hu.progmasters.bskinteriordesignbackend.exception.AboutEntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;
    private final CloudinaryService cloudinaryService;


    public AboutResponseDto getContentSection() {
        return aboutRepository.findTopByOrderByIdAsc()
                .map(entity -> AboutResponseDto.anAboutResponse()
                        .withId(entity.getId())
                        .withContent(entity.getContent())
                        .withProfileImageUrl(entity.getProfileImageUrl())
                        .build())
                .orElse(AboutResponseDto.anAboutResponse()
                        .withContent("Default about content")
                        .build());
    }

    public void updateContentSection(AboutUpdateDto command) {
        AboutEntity about = aboutRepository.findById(command.getId())
                .orElseThrow(() -> new AboutEntityNotFoundException(command.getId()));

        if (command.getContent() != null && !command.getContent().isBlank()) {
            about.setContent(command.getContent());
        }

        if (command.getProfileImage() != null && !command.getProfileImage().isEmpty()) {
            CloudinaryUploadResultDto result = cloudinaryService.uploadImage(command.getProfileImage());

            if (about.getProfileImagePublicId() != null) {
                cloudinaryService.deleteImage(about.getProfileImagePublicId());
            }

            about.setProfileImageUrl(result.getImageUrl());
            about.setProfileImagePublicId(result.getPublicId());
        }

        aboutRepository.save(about);
    }
}
