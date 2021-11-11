package com.microservicio.app.test.backend.repository;

import com.microservicio.app.test.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNombre(String nombre);
}
