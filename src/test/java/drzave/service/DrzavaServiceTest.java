package hr.ispit.drzave.service;
import hr.ispit.drzave.dto.*; import hr.ispit.drzave.entity.Kontinent; import hr.ispit.drzave.exception.NotFoundException; import hr.ispit.drzave.repository.*; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.transaction.annotation.Transactional; import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional class DrzavaServiceTest {
 @Autowired DrzavaService service; @Autowired KontinentRepository kontinenti;
 @Test void crudRadi(){Kontinent k=kontinenti.save(new Kontinent("Testni kontinent"));DrzavaDto d=service.create(new DrzavaRequest("Testna država",123,k.getId()));assertEquals("Testna država",service.findById(d.id()).naziv());assertEquals(456,service.update(d.id(),new DrzavaRequest("Nova država",456,k.getId())).brojStanovnika());service.delete(d.id());assertThrows(NotFoundException.class,()->service.findById(d.id()));}
}
