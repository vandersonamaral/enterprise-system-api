package com.companies.enterprise.domain.services;

import com.companies.enterprise.dtos.out.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TokenService extends UserDetailsService {

    public String getToken(AuthDto auth);

    public String validateToken(String token);
}
