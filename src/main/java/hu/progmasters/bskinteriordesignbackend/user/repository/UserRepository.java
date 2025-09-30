package hu.progmasters.bskinteriordesignbackend.user.repository;

import hu.progmasters.bskinteriordesignbackend.user.model.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
