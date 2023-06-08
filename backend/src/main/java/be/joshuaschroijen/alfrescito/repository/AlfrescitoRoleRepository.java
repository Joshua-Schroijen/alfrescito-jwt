package be.joshuaschroijen.alfrescito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.joshuaschroijen.alfrescito.model.AlfrescitoRole;

public interface AlfrescitoRoleRepository extends JpaRepository<AlfrescitoRole, Long> {
    AlfrescitoRole findByName(String name);
}