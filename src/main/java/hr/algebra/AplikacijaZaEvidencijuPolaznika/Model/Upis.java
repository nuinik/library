package hr.algebra.AplikacijaZaEvidencijuPolaznika.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int upisId;

    @ManyToOne
    @JoinColumn(name = "programObrazovanjaId", nullable = false)
    private ProgramObrazovanja programObrazovanja;

    @ManyToOne
    @JoinColumn(name = "polaznikId")
    private Polaznik polaznik;
}
