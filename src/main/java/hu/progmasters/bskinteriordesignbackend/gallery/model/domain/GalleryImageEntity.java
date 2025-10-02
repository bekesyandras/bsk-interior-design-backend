package hu.progmasters.bskinteriordesignbackend.gallery.model.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gallery_image")
@Getter
@Setter
@Builder(builderMethodName = "aGalleryImage", toBuilder = true, setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class GalleryImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageUrl;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @PrePersist
    protected void onCreate() {
        this.uploadedAt = LocalDateTime.now();
    }

}
