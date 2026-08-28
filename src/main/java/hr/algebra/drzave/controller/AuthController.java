package hr.ispit.drzave.controller;
import hr.ispit.drzave.dto.*; 
import hr.ispit.drzave.security.JwtService; 
import jakarta.validation.Valid; 
import org.springframework.http.*; 
import org.springframework.security.authentication.*; 
import org.springframework.security.core.userdetails.*; 
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/api/auth") 
public class AuthController {
private final AuthenticationManager auth; 
private final UserDetailsService users; 
private final JwtService jwt;
 
public AuthController(AuthenticationManager a,UserDetailsService u,JwtService j){
 auth=a;users=u;jwt=j;
}
 
 @PostMapping("/login") 
 public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthRequest r){
  auth.authenticate(new UsernamePasswordAuthenticationToken(r.username(),r.password()));UserDetails u=users.loadUserByUsername(r.username());return ResponseEntity.ok(new TokenResponse(jwt.access(u),jwt.refresh(u)));}

 @PostMapping("/refresh") 
 public ResponseEntity<TokenResponse> refresh(@RequestBody String refresh){UserDetails u=users.loadUserByUsername(jwt.username(refresh));if(!jwt.valid(refresh,u,"refresh"))throw new BadCredentialsException("Neispravan refresh token");return ResponseEntity.ok(new TokenResponse(jwt.access(u),jwt.refresh(u)));}
}

