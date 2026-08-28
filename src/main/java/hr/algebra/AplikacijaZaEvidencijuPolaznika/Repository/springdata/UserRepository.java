package hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
