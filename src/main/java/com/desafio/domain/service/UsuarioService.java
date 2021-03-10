package com.desafio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.domain.exception.NegocioException;
import com.desafio.domain.exception.UsuarioNaoEncontradoException;
import com.desafio.domain.model.Usuario;
import com.desafio.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByCpf(usuario.getCpf());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException( String.format("Já existe um usuário cadastrado com o cpf %s", usuario.getCpf()));
		} 
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try{
			
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		
		} catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(id);
		}
		
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				                .orElseThrow( () -> 
				                    new UsuarioNaoEncontradoException(usuarioId)
				                 );
	}

}
