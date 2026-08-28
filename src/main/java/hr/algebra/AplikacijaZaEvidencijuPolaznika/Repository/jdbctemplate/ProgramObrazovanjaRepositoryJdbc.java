package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.ProgramObrazovanja;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProgramObrazovanjaRepositoryJdbc implements ProgramObrazovanjaRepository {

    private JdbcTemplate jdbcTemplate;

    public List<ProgramObrazovanja> findAll() {
        String sql = "SELECT * FORM program_obrazovanja";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProgramObrazovanja.class));
    }

    public Optional<ProgramObrazovanja> findById(int id) {
        String sql = "SELECT * FROM program_obrazovanja WHERE programObrazovanjaId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProgramObrazovanja.class), id)
                .stream().findFirst();
    }

    public int save(ProgramObrazovanja programObrazovanja) {
        String sql = "INSERT INTO program_obrazovanja (naziv, CSVET) VALUES (?, ?)";
        return jdbcTemplate.update(sql, programObrazovanja.getNaziv(), programObrazovanja.getCsvet());
    }

    public int update(ProgramObrazovanja programObrazovanja) {
        String sql = "UPDATE INTO program_obrazovanja SET naziv = ?, CSVET = ? WHERE programObrazovanjaId = ?";
        return jdbcTemplate.update(sql, programObrazovanja.getNaziv(), programObrazovanja.getCsvet(),
                programObrazovanja.getProgramObrazovanjaId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM program_obrazovanja WHERE programObrazovanjaId = ?";
        return jdbcTemplate.update(sql, id);
    }
}
