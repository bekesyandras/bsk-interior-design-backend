package hu.progmasters.bskinteriordesignbackend.about.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "about")
@Getter
@Setter
@Builder(builderMethodName = "anAbout", toBuilder = true, setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class AboutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
