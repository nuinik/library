package hr.ispit.biljke.service;
import hr.ispit.biljke.dto.*; import hr.ispit.biljke.entity.TipBiljke; import hr.ispit.biljke.exception.NotFoundException; import hr.ispit.biljke.repository.*; import org.junit.jupiter.api.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.transaction.annotation.Transactional; import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional class BiljkaServiceTest {
 @Autowired BiljkaService service; @Autowired TipBiljkeRepository tipovi;
 @Test void crudRadi(){TipBiljke t=tipovi.save(new TipBiljke("Testni tip"));BiljkaDto b=service.create(new BiljkaRequest("Ruža",12.5,t.getId()));assertEquals("Ruža",service.findById(b.id()).naziv());assertEquals(15.0,service.update(b.id(),new BiljkaRequest("Tulipan",15,t.getId())).cijena());service.delete(b.id());assertThrows(NotFoundException.class,()->service.findById(b.id()));}
}
