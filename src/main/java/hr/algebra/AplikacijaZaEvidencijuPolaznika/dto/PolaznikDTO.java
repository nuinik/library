package hr.algebra.AplikacijaZaEvidencijuPolaznika.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PolaznikDTO {
    private int polaznikID;

    @NotBlank(message = "Ime is mandatory")
    @Size(max = 50, message = "Ime cannot exceed 50 characters")
    private String ime;

    @NotBlank(message = "Prezime is mandatory")
    @Size(max = 50, message = "Prezime cannot exceed 50 characters")
    private String prezime;
}
