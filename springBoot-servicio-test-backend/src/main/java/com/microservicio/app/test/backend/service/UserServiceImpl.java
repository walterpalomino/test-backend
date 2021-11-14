package com.microservicio.app.test.backend.service;

import com.microservicio.app.test.backend.entity.Role;
import com.microservicio.app.test.backend.entity.Usuario;
import com.microservicio.app.test.backend.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNombre(username);

        if(Objects.isNull(usuario)){
            log.error("El usuario " + username + " que se intenta loguear no esta registrado");
            throw new UsernameNotFoundException("El usuario " + username + " que se intenta loguear no esta registrado");
        }

        List<GrantedAuthority> roles = usuario.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                        .peek(autority -> log.info("Role " + autority.getAuthority()))
                        .collect(Collectors.toList());

        log.info("Usuario autenticado " + username);

        return new User(usuario.getNombre(), usuario.getClave(), roles);

    }
}
