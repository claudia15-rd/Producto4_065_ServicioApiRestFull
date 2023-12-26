package com.Prod4.servicio;

import java.util.List;

import com.Prod4.modeloUsuarioRol.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.Prod4.controladores.dto.UsuarioRegistroDTO;



public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
