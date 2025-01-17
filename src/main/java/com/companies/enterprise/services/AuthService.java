package com.companies.enterprise.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.companies.enterprise.dto.AuthDto;
import com.companies.enterprise.entities.User;
import com.companies.enterprise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class AuthService implements TokenService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getToken(AuthDto auth) {

        User user = userRepository.findByLogin(auth.login());

        return generateTokenJwt(user);
    }

    public String generateTokenJwt(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("enterprise.jwt.secret");

            return JWT.create()
                    .withIssuer("enterprise-system-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token! " + exception.getMessage());

        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("enterprise.jwt.secret");

            return JWT.require(algorithm)
                    .withIssuer("enterprise-system-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "Token invalido  " + exception.getMessage();
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
