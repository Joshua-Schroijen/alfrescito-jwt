package be.joshuaschroijen.alfrescito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;

public interface AlfrescitoUserRepository extends JpaRepository<AlfrescitoUser, Long> {
    default public AlfrescitoUser findOneByUsername(String username) {
        return findOneByEmail(username);
    }

    public AlfrescitoUser findOneByEmail(String email);
}