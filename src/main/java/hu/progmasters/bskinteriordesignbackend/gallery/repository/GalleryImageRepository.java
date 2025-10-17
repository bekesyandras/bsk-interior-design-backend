package hu.progmasters.bskinteriordesignbackend.gallery.repository;


import hu.progmasters.bskinteriordesignbackend.gallery.model.domain.GalleryImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryImageRepository extends JpaRepository<GalleryImageEntity, Long> {
    Page<GalleryImageEntity> findAll(Pageable pageable);

    Optional<GalleryImageEntity> findTopByOrderByDisplayOrderDesc();
}
