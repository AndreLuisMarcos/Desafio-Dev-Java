package com.desafio.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.desafio.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByCpf(String cpf);

}
