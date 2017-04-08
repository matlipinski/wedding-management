package pl.mlipinski.wedding.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlipinski.wedding.management.domain.entity.Invitation;

/**
 * Repository for {@link pl.mlipinski.wedding.management.domain.entity.Invitation}
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long> {
}
