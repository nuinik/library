package hr.ispit.biljke.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.*;
@Service public class JwtService {
 private final SecretKey key; private final long accessMs; private final long refreshMs;
 public JwtService(@Value("${jwt.secret}") String secret,@Value("${jwt.access-ms}") long a,@Value("${jwt.refresh-ms}") long r){key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));accessMs=a;refreshMs=r;}
 public String access(UserDetails u){return token(u,accessMs,"access");} public String refresh(UserDetails u){return token(u,refreshMs,"refresh");}
 private String token(UserDetails u,long ms,String type){Date now=new Date();return Jwts.builder().subject(u.getUsername()).claim("type",type).issuedAt(now).expiration(new Date(now.getTime()+ms)).signWith(key).compact();}
 public String username(String t){return claims(t).getSubject();} public String type(String t){return claims(t).get("type",String.class);}
 public boolean valid(String t,UserDetails u,String type){try{return username(t).equals(u.getUsername())&&type(t).equals(type)&&claims(t).getExpiration().after(new Date());}catch(JwtException|IllegalArgumentException e){return false;}}
 private Claims claims(String t){return Jwts.parser().verifyWith(key).build().parseSignedClaims(t).getPayload();}
}

