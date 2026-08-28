package hr.algebra.AplikacijaZaEvidencijuPolaznika.Service;

import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.CustomUserDetails;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Model.UserInfo;
import hr.algebra.AplikacijaZaEvidencijuPolaznika.Repository.springdata.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("Could not found user!");
        }
        log.info("User Authenticated Successfully!");
        return new CustomUserDetails(user);
    }
}
