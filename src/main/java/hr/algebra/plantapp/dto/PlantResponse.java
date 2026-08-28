package hr.algebra.plantapp.dto;
import java.math.BigDecimal;
public record PlantResponse(Long id, String name, BigDecimal price, Long typeId, String typeName) {}
