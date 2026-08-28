package hr.ispit.drzave.entity;
import jakarta.persistence.*;
@Entity public class Kontinent {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String naziv;
 public Kontinent(){} public Kontinent(String naziv){this.naziv=naziv;}
 public Long getId(){return id;} public String getNaziv(){return naziv;} public void setNaziv(String naziv){this.naziv=naziv;}
}

