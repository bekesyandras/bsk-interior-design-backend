package hu.progmasters.bskinteriordesignbackend.about.repository;

import hu.progmasters.bskinteriordesignbackend.about.model.domain.AboutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Long> {
    Optional<AboutEntity> findTopByOrderByIdAsc();

}
