package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Usuario;
import com.google.common.base.Optional;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
