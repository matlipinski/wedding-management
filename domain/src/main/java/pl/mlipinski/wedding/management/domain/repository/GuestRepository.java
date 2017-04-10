package pl.mlipinski.wedding.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mlipinski.wedding.management.domain.entity.Guest;
import pl.mlipinski.wedding.management.domain.entity.Invitation;

/**
 * Repository for {@link pl.mlipinski.wedding.management.domain.entity.Guest}
 */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
