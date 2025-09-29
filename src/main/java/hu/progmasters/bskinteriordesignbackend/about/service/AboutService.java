package hu.progmasters.bskinteriordesignbackend.about.service;

import hu.progmasters.bskinteriordesignbackend.about.model.domain.AboutEntity;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutContentUpdateDto;
import hu.progmasters.bskinteriordesignbackend.about.model.dto.AboutResponseDto;
import hu.progmasters.bskinteriordesignbackend.about.repository.AboutRepository;
import hu.progmasters.bskinteriordesignbackend.exception.AboutEntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;

    public AboutResponseDto getContent() {
        return aboutRepository.findTopByOrderByIdAsc()
                .map(entity -> AboutResponseDto.anAboutResponse()
                        .withId(entity.getId())
                        .withContent(entity.getContent())
                        .build())
                .orElse(AboutResponseDto.anAboutResponse()
                        .withContent("Default about content")
                        .build());
    }

    public void updateContent(AboutContentUpdateDto command) {
        AboutEntity about = aboutRepository.findById(command.getId())
                .orElseThrow(() -> new AboutEntityNotFoundException(command.getId()));
        about.setContent(command.getContent());
    }
}
