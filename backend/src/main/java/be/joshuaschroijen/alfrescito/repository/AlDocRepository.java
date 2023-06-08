package be.joshuaschroijen.alfrescito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import be.joshuaschroijen.alfrescito.model.AlDoc;
import be.joshuaschroijen.alfrescito.model.AlfrescitoUser;

public interface AlDocRepository extends CrudRepository<AlDoc, Long> {
    List<AlDoc> findByOwner(AlfrescitoUser owner);

    List<AlDoc> findByFilePath(String filePath);

    Optional<AlDoc> findById(Long id);
}