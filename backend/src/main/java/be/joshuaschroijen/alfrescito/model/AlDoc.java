package be.joshuaschroijen.alfrescito.model;

import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.joshuaschroijen.alfrescito.utils.FilePathSerializer;

@Entity
public class AlDoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AlfrescitoUser owner;

    @Column(nullable = false)
    @JsonSerialize(using = FilePathSerializer.class)
    private String filePath;

    @Column(nullable = true)
    private String summary;

    @OneToMany(mappedBy = "alDoc", cascade = CascadeType.ALL)
    private List<NamedEntity> namedEntities;

    protected AlDoc() {
    }

    public AlDoc(Long id, String filePath, String summary, List<NamedEntity> namedEntities) {
        this.id = id;
        this.filePath = filePath;
        this.summary = summary;
        this.namedEntities = namedEntities;
    }

    @Override
    public String toString() {
        return String.format(
                "AlDoc[id=%d, filePath='%s']",
                id, filePath);
    }

    public Long getId() {
        return this.id;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getSummary() {
        return this.summary;
    }

    public List<NamedEntity> getNamedEntities() {
        return this.namedEntities;
    }
}