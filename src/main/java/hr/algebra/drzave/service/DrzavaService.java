package hr.ispit.drzave.service;
import hr.ispit.drzave.dto.*; import hr.ispit.drzave.entity.*; import hr.ispit.drzave.exception.NotFoundException; import hr.ispit.drzave.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service @Transactional public class DrzavaService {
 private final DrzavaRepository drzave; private final KontinentRepository kontinenti;
 public DrzavaService(DrzavaRepository d,KontinentRepository k){drzave=d;kontinenti=k;}
 @Transactional(readOnly=true) public List<DrzavaDto> findAll(){return drzave.findAll().stream().map(this::map).toList();}
 @Transactional(readOnly=true) public DrzavaDto findById(Long id){return map(get(id));}
 public DrzavaDto create(DrzavaRequest r){if(drzave.existsByNazivIgnoreCase(r.naziv()))throw new IllegalArgumentException("Država već postoji"); Drzava d=new Drzava(); apply(d,r); return map(drzave.save(d));}
 public DrzavaDto update(Long id,DrzavaRequest r){Drzava d=get(id); apply(d,r); return map(drzave.save(d));}
 public void delete(Long id){drzave.delete(get(id));}
 private Drzava get(Long id){return drzave.findById(id).orElseThrow(()->new NotFoundException("Država nije pronađena: "+id));}
 private void apply(Drzava d,DrzavaRequest r){Kontinent k=kontinenti.findById(r.kontinentId()).orElseThrow(()->new NotFoundException("Kontinent nije pronađen"));d.setNaziv(r.naziv().trim());d.setBrojStanovnika(r.brojStanovnika());d.setKontinent(k);}
 private DrzavaDto map(Drzava d){return new DrzavaDto(d.getId(),d.getNaziv(),d.getBrojStanovnika(),d.getKontinent().getId(),d.getKontinent().getNaziv());}
}

