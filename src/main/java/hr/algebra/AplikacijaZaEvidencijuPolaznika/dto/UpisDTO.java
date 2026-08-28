package hr.algebra.AplikacijaZaEvidencijuPolaznika.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpisDTO {

    private int upisId;

    @NotNull(message = "ProgramObrazovanjaId je obavezan")
    private int idProgramObrazovanja;

    @NotNull(message = "PolaznikId je obavezan")
    private int idPolaznik;
}
