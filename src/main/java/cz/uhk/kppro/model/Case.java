package cz.uhk.kppro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @OneToOne(optional = true)
    @JoinColumn(name = "lawyer_id")
    private Lawyer lawyer;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private User defendant;

    @ManyToOne
    private User plaintiff;

    @OneToMany(mappedBy = "relatedCase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseDocument> documents = new ArrayList<>();

    public User getDefendant() {
        return defendant;
    }

    public void setDefendant(User defendant) {
        this.defendant = defendant;
    }

    public User getPlaintiff() {
        return plaintiff;
    }

    public void setPlaintiff(User plaintiff) {
        this.plaintiff = plaintiff;
    }

    public List<CaseDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<CaseDocument> documents) {
        this.documents = documents;
    }

    // Getters and Setters
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

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}