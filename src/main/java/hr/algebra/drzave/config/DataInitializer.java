package hr.ispit.drzave.config;
import hr.ispit.drzave.entity.*; import hr.ispit.drzave.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*;
@Configuration public class DataInitializer { @Bean CommandLineRunner data(KontinentRepository k,DrzavaRepository d){return args->{if(k.count()==0){for(String n:new String[]{"Afrika","Azija","Europa","Sjeverna Amerika","Australija","Antarktika","Južna Amerika"})k.save(new Kontinent(n));}};} }

