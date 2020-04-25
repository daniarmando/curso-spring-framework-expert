package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.helper.usuario.UsuariosQueries;
import com.google.common.base.Optional;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	Optional<Usuario> findByEmail(String email);

}
