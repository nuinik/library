package hr.algebra.AplikacijaZaEvidencijuPolaznika.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProgramObrazovanjaDTO {

    private int programObrazovanjaId;

    @NotBlank(message = "Naziv is mandatory")
    @Size(max = 10, message = "Naziv ne može biti veći od 10")
    private String naziv;

    @Max(value = 100, message = "CSVET mora biti manji ili jednak 100")
    private int csvet;
}
