package com.desafio.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.ResourceUriHelper;
import com.desafio.api.assembler.UsuarioInputDisassembler;
import com.desafio.api.assembler.UsuarioModelAssembler;
import com.desafio.api.model.UsuarioModel;
import com.desafio.api.model.input.UsuarioInput;
import com.desafio.api.openapi.controller.UsuarioControllerOpenApi;
import com.desafio.domain.model.Usuario;
import com.desafio.domain.repository.UsuarioRepository;
import com.desafio.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/api")
public class UsuarioController implements UsuarioControllerOpenApi {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	@GetMapping("/user")
	public Page<UsuarioModel> listar(@PageableDefault(size = 10) Pageable pageable) { 
		Page<Usuario> usuariosPage = usuarioRepository.findAll(pageable);
		
		List<UsuarioModel> usuariosModel = usuarioModelAssembler.toCollectionModel(usuariosPage.getContent());
		
		Page<UsuarioModel> usuariosModelPage = new PageImpl<>(usuariosModel, pageable, usuariosPage.getTotalElements());
		
		return usuariosModelPage;
	}
	
	@Override
	@GetMapping("/users/{id}")
	public ResponseEntity<UsuarioModel> buscar(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarOuFalhar(id);
		
		return ResponseEntity.ok()
			       .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
				   .body(usuarioModelAssembler.toModel(usuario));
	}
	
	@Override
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = usuarioService.salvar(usuario);
		
		UsuarioModel usuarioModel = usuarioModelAssembler.toModel(usuario);
		
		ResourceUriHelper.addUriInResponseHeader(usuarioModel.getId());
		
		return usuarioModel;
	}
	
	@Override
	@PutMapping("/user/{id}")
	public UsuarioModel atualizar(@PathVariable Long id,
			                      @RequestBody @Valid UsuarioInput usuarioInput) {
		
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(id);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioService.salvar(usuarioAtual);
		
		return usuarioModelAssembler.toModel(usuarioAtual);
	}
	
	@Override
	@DeleteMapping("/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioService.excluir(id);
	}
	
}
