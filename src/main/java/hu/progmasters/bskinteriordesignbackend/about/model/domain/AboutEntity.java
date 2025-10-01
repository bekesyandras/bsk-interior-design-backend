package hu.progmasters.bskinteriordesignbackend.about.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "content", columnDefinition = "TEXT")
    @Size(max = 5000)
    private String content;
}
