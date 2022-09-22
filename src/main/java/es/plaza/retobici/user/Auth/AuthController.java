package es.plaza.retobici.user.Auth;

import es.plaza.retobici.config.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
