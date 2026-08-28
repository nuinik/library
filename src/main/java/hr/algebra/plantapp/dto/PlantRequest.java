package hr.algebra.plantapp.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record PlantRequest(@NotBlank(message="Naziv biljke je obavezan.") String name,
    @NotNull(message="Cijena je obavezna.") @DecimalMin(value="0.01", message="Cijena mora biti veća od nule.") BigDecimal price,
    @NotNull(message="Tip biljke je obavezan.") Long typeId) {}
