package hr.ispit.drzave.entity;

import jakarta.persistence.*;

@Entity
public class Drzava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String naziv;
    @Column(nullable = false)
    private long brojStanovnika;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "kontinent_id")
    private Kontinent kontinent;

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String v) {
        naziv = v;
    }

    public long getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(long v) {
        brojStanovnika = v;
    }

    public Kontinent getKontinent() {
        return kontinent;
    }

    public void setKontinent(Kontinent v) {
        kontinent = v;
    }
}

