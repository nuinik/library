package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Upis;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UpisRepositoryJdbc {

    private JdbcTemplate jdbcTemplate;

    public List<Upis> findAll() {
        String sql = "SELECT * FROM upis";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Upis.class));
    }

    public Optional<Upis> findById(int id) {
        String sql = "SELECT * FROM upis WHERE upisId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Upis.class), id)
                .stream().findFirst();
    }

    public int save (Upis upis) {
        String sql = "INSERT INTO upis (IDProgramObrazovanja, IDPolaznik) VALUES (?, ?)";
        return jdbcTemplate.update(sql,
                upis.getProgramObrazovanja().getProgramObrazovanjaId(), upis.getPolaznik().getPolaznikId());
    }

    public int update(Upis upis) {
        String sql = "UPDATE upis SET IDProgramObrazovanja = ?, IDPolaznik = ? WHERE upisId = ?";
        return jdbcTemplate.update(sql,
                upis.getProgramObrazovanja().getProgramObrazovanjaId(), upis.getPolaznik().getPolaznikId(), upis.getUpisId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM upis WHERE upisId = ?";
        return jdbcTemplate.update(sql, id);
    }
}
