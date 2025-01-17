package com.companies.enterprise.services;

import com.companies.enterprise.dto.AuthDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TokenService extends UserDetailsService {

    public String getToken(AuthDto auth);

    public String validateToken(String token);
}
