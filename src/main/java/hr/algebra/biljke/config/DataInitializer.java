package hr.ispit.biljke.config;
import hr.ispit.biljke.entity.*; import hr.ispit.biljke.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*;
@Configuration public class DataInitializer { @Bean CommandLineRunner data(TipBiljkeRepository k,BiljkaRepository d){return args->{if(k.count()==0){for(String n:new String[]{"Voće","Povrće","Ukras"})k.save(new TipBiljke(n));}};} }
