package com.companies.enterprise.services;

import com.companies.enterprise.dto.UserDto;
import com.companies.enterprise.entities.User;
import com.companies.enterprise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto save(UserDto user) {

        User UsuarioJaExiste = userRepository.findByLogin(user.login());
        if (UsuarioJaExiste != null) {
            throw new RuntimeException("Usuario ja existe");
        }
        var passwordHas = passwordEncoder.encode(user.password());
        User entity = new User(user.name(), user.login(), passwordHas, user.role());
        User newUser = userRepository.save(entity);
        return new UserDto(newUser.getName(), newUser.getLogin(), newUser.getPassword(), newUser.getRole());

    }
}
