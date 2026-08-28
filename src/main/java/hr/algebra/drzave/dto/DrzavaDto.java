package hr.ispit.drzave.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DrzavaDTO {

    private Long drzavaID;

    @NotBlank(message = "Naziv države je obavezan")
    @Size(max = 50, message = "Naziv države ne može imati više od 50 znakova")
    private String naziv;

    @NotNull(message = "Broj stanovnika je obavezan")
    @Min(value = 0, message = "Broj stanovnika ne može biti negativan")
    private Long brojStanovnika;

    @NotNull(message = "ID kontinenta je obavezan")
    private Long continentID;

    private String continentNaziv;
}
