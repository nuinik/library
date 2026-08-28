package hr.ispit.biljke.service;
import hr.ispit.biljke.dto.*; import hr.ispit.biljke.entity.*; import hr.ispit.biljke.exception.NotFoundException; import hr.ispit.biljke.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service @Transactional public class BiljkaService {
 private final BiljkaRepository biljke; private final TipBiljkeRepository tipBiljkei;
 public BiljkaService(BiljkaRepository d,TipBiljkeRepository k){biljke=d;tipBiljkei=k;}
 @Transactional(readOnly=true) public List<BiljkaDto> findAll(){return biljke.findAll().stream().map(this::map).toList();}
 @Transactional(readOnly=true) public BiljkaDto findById(Long id){return map(get(id));}
 public BiljkaDto create(BiljkaRequest r){if(biljke.existsByNazivIgnoreCase(r.naziv()))throw new IllegalArgumentException("Biljka već postoji"); Biljka d=new Biljka(); apply(d,r); return map(biljke.save(d));}
 public BiljkaDto update(Long id,BiljkaRequest r){Biljka d=get(id); apply(d,r); return map(biljke.save(d));}
 public void delete(Long id){biljke.delete(get(id));}
 private Biljka get(Long id){return biljke.findById(id).orElseThrow(()->new NotFoundException("Biljka nije pronađena: "+id));}
 private void apply(Biljka d,BiljkaRequest r){TipBiljke k=tipBiljkei.findById(r.tipBiljkeId()).orElseThrow(()->new NotFoundException("TipBiljke nije pronađen"));d.setNaziv(r.naziv().trim());d.setCijena(r.cijena());d.setTipBiljke(k);}
 private BiljkaDto map(Biljka d){return new BiljkaDto(d.getId(),d.getNaziv(),d.getCijena(),d.getTipBiljke().getId(),d.getTipBiljke().getNaziv());}
}

