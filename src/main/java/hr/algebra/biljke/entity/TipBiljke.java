package hr.ispit.biljke.entity;
import jakarta.persistence.*;
@Entity public class TipBiljke {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String naziv;
 public TipBiljke(){} public TipBiljke(String naziv){this.naziv=naziv;}
 public Long getId(){return id;} public String getNaziv(){return naziv;} public void setNaziv(String naziv){this.naziv=naziv;}
}

