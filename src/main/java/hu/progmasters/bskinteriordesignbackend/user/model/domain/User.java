package hu.progmasters.bskinteriordesignbackend.user.model.domain;

import hu.progmasters.bskinteriordesignbackend.user.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder(builderMethodName = "aUser", toBuilder = true, setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


}
