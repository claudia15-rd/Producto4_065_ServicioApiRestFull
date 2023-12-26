package com.Prod4.RepositorioUsuario;

import com.Prod4.modeloUsuarioRol.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioRepositorio extends JpaRepository <Usuario, Long>{
    public Usuario findByEmail(String email);
}
