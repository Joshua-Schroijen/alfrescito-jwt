package be.joshuaschroijen.alfrescito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;

@Repository
public interface AlfrescitoUserRepository extends JpaRepository<AlfrescitoUser, Long> {
    default public AlfrescitoUser findOneByUsername(String username) {
        return findOneByEmail(username);
    }

    public AlfrescitoUser findOneByEmail(String email);
}