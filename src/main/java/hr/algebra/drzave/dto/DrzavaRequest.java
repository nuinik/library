package hr.ispit.drzave.dto;
import jakarta.validation.constraints.*;
public record DrzavaRequest(@NotBlank String naziv, @Positive long brojStanovnika, @NotNull Long kontinentId) {}

