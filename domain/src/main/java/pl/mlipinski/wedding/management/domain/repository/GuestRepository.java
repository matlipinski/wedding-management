package pl.mlipinski.wedding.management.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.mlipinski.wedding.management.domain.entity.Guest;

import java.util.List;

/**
 * Repository for {@link pl.mlipinski.wedding.management.domain.entity.Guest}
 */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findById(long guestId);

    @Query("SELECT g from Guest g " +
            "WHERE lower(g.firstName) like CONCAT('%',lower(:searchText),'%')  " +
            "OR lower(g.lastName) like CONCAT('%',lower(:searchText),'%')")
    List<Guest> findByFirstNameOrLastName(@Param("searchText") String searchText);
}
