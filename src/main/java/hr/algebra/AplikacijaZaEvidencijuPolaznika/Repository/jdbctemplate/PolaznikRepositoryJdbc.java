package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.jdbctemplate;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.Polaznik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PolaznikRepositoryJdbc implements PolaznikRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Polaznik> findAll() {
        String sql = "SELECT * FROM polaznik";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Polaznik.class));
    }

    public Optional<Polaznik> findById(int id) {
        String sql = "SELECT * FROM polaznik WHERE polaznikID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Polaznik.class), id)
                .stream().findFirst();
    }

    public int save(Polaznik polaznik) {
        String sql = "INSERT INTO polaznik (ime, prezime) VALUES (?, ?)";
        return jdbcTemplate.update(sql, polaznik.getIme(), polaznik.getPrezime());
    }

    public int update(Polaznik polaznik) {
        String sql = "UPDATE polaznik SET ime = ?, prezime = ? WHERE polaznikID = ?";
        return jdbcTemplate.update(sql, polaznik.getIme(), polaznik.getPrezime(), polaznik.getPolaznikId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM polaznik WHERE polaznikID = ?";
        return jdbcTemplate.update(sql, id);
    }

//    public int deleteById(int id) {
//        String sql = "DELETE FROM polaznik WHERE polaznikID = ?";
//        return jdbcTemplate.update(sql, id);
//    }
}
