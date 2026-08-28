package hr.ispit.biljke.dto;
import jakarta.validation.constraints.*;
public record BiljkaRequest(@NotBlank String naziv, @Positive double cijena, @NotNull Long tipBiljkeId) {}

