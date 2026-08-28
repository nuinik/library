package hr.ispit.biljke.entity;
import jakarta.persistence.*;
@Entity public class Biljka {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String naziv;
 @Column(nullable=false) private double cijena;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) @JoinColumn(name="tipBiljke_id") private TipBiljke tipBiljke;
 public Long getId(){return id;} public String getNaziv(){return naziv;} public void setNaziv(String v){naziv=v;}
 public long getCijena(){return cijena;} public void setCijena(double v){cijena=v;}
 public TipBiljke getTipBiljke(){return tipBiljke;} public void setTipBiljke(TipBiljke v){tipBiljke=v;}
}

