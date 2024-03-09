package com.facenet.shipsregistry.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.facenet.shipsregistry.modal.LoginResponse;
import com.facenet.shipsregistry.modal.RoleDTO;
import com.facenet.shipsregistry.modal.UserDTO;
import com.facenet.shipsregistry.principal.UserPrincipal;
import com.facenet.shipsregistry.request.ChangePasswordRequest;
import com.facenet.shipsregistry.request.LoginRequestBody;
import com.facenet.shipsregistry.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/auth")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Environment env;

    /**
     *
     * @param loginRequestBody
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestBody loginRequestBody,
                                   HttpServletRequest request) {
        String secret = env.getProperty("secret");
        log.info(loginRequestBody.toString());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestBody.getUsername(),
                            loginRequestBody.getPassword())
            );
            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
            assert secret != null;
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .withClaim("fullName", user.getFullName())
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken, user.getUsername()));
        } catch (BadCredentialsException exception) {
            log.error("{}", exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.badRequest().body("Tài khoản mật khẩu không chính xác");
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            String message = authService.updatePassword(request);
            return ResponseEntity.ok().body(message);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
