package ma.gov.stagiaire.repositories;

import ma.gov.stagiaire.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin(String login);
    boolean existsByLogin(String login);
}
