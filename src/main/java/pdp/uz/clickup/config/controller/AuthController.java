package pdp.uz.clickup.config.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pdp.uz.clickup.entity.User;
import pdp.uz.clickup.payload.ApiResponse;
import pdp.uz.clickup.payload.LoginDTO;
import pdp.uz.clickup.payload.RegisterDTO;
import pdp.uz.clickup.security.JwtProvider;
import pdp.uz.clickup.service.AuthService;


@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    final AuthService authService;
    final AuthenticationManager authenticationManager;
    final JwtProvider jwtProvider;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/register")
    public HttpEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {

        ApiResponse apiResponse = authService.registerEmployee(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );
            User user = (User) authenticate.getPrincipal();
            String token = jwtProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse("Email or password not match!", false));
        }
    }

    @PutMapping(value = "/verifyEmail")
    public HttpEntity<?> verifyEmail(@RequestParam String email, @RequestParam String emailCode) {

        ApiResponse apiResponse = authService.verifyEmail(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
