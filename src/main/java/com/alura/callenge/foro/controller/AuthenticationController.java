package com.alura.callenge.foro.controller;


import com.alura.callenge.foro.domain.user.User;
import com.alura.callenge.foro.domain.user.UserAuthenticationData;
import com.alura.callenge.foro.infra.security.JWTTokenData;
import com.alura.callenge.foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuthentication(@RequestBody @Valid UserAuthenticationData userAuthenticationData){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userAuthenticationData.login(),
                userAuthenticationData.password());
        var authUser = authenticationManager.authenticate(authenticationToken);

        var JWTtoken = tokenService.generateToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }
}