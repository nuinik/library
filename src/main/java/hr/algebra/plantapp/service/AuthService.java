package hr.algebra.plantapp.service;

import hr.algebra.plantapp.dto.AuthRequest;
import hr.algebra.plantapp.dto.AuthResponse;
import hr.algebra.plantapp.entity.AppUser;
import hr.algebra.plantapp.repository.UserRepository;
import hr.algebra.plantapp.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        AppUser user = userRepository.findByUsername(request.username()).orElseThrow();
        return createResponse(user);
    }

    public AuthResponse refresh(String refreshToken) {
        try {
            if (!"REFRESH".equals(jwtService.extractTokenType(refreshToken))) {
                throw new IllegalArgumentException("Poslani token nije refresh token.");
            }

            String username = jwtService.extractUsername(refreshToken);
            AppUser user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("Korisnik ne postoji."));

            if (!jwtService.isValid(refreshToken, user)) {
                throw new IllegalArgumentException("Refresh token nije ispravan ili je istekao.");
            }
            return createResponse(user);
        } catch (IllegalArgumentException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new IllegalArgumentException("Refresh token nije ispravan ili je istekao.");
        }
    }

    private AuthResponse createResponse(AppUser user) {
        return new AuthResponse(jwtService.generateAccessToken(user),
                jwtService.generateRefreshToken(user), "Bearer");
    }
}
