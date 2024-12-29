package cz.uhk.kppro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "casedocuments")
public class CaseDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    private String text;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private Case relatedCase;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Case getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(Case relatedCase) {
        this.relatedCase = relatedCase;
    }
}
