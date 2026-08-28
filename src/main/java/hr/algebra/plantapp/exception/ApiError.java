package hr.algebra.plantapp.exception;
import java.time.LocalDateTime;
public record ApiError(LocalDateTime timestamp, int status, String message) {}
