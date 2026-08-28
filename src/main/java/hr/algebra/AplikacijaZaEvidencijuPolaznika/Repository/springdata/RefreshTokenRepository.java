package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByToken(String token);
}
