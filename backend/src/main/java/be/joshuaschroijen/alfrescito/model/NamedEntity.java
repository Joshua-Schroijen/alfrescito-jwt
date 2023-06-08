package be.joshuaschroijen.alfrescito.model;

import jakarta.persistence.*;

@Entity
public class NamedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "aldoc_id")
    private AlDoc alDoc;

    protected NamedEntity() {
    }

    public NamedEntity(Long id, String name, AlDoc aldoc) {
        this.id = id;
        this.name = name;
        this.alDoc = aldoc;
    }

    @Override
    public String toString() {
        return String.format(
                "NamedEntity[id=%d, name='%s']",
                id, name);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public AlDoc getAlDoc() {
        return this.alDoc;
    }
}