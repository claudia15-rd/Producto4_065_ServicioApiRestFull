package com.Prod4.ServicioUsuario;

import com.Prod4.controladores.dto.UsuarioRegistroDTO;
import com.Prod4.modeloUsuarioRol.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UsuarioServicio extends UserDetailsService {
    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();



}
