package pl.mlipinski.wedding.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.mlipinski.wedding.management.domain.entity.Invitation;

import java.util.List;

/**
 * Repository for {@link pl.mlipinski.wedding.management.domain.entity.Invitation}
 */
@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {


    @Query(value = "SELECT * " +
           "FROM invitation ii " +
           "WHERE ii.id NOT IN " +
           "      (SELECT i.id" +
           "       FROM invitation i" +
           "       WHERE (i.adult_count + i.children_count) =" +
           "             (SELECT count(*)" +
           "              FROM guest g" +
           "              WHERE (g.invitation_id = i.invitation_id)" +
           "              GROUP BY g.invitation_id))", nativeQuery = true)
    List<Invitation> findNotAssigned();

}
