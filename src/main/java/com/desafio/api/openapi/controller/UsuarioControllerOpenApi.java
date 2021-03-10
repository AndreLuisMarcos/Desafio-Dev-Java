package com.desafio.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.desafio.api.exceptionhandler.Problem;
import com.desafio.api.model.UsuarioModel;
import com.desafio.api.model.input.UsuarioInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Usuários")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Lista os usuários")
	Page<UsuarioModel> listar(Pageable pageable);

	@ApiOperation("Busca um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do usuário inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	ResponseEntity<UsuarioModel> buscar(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id);

	@ApiOperation("Cadastra um usuário")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Usuário cadastrado"),
	})
	UsuarioModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true)
			UsuarioInput usuarioInput);
	
	@ApiOperation("Atualiza um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Usuário atualizado"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	UsuarioModel atualizar(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id,
			
			@ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados",
				required = true)
			UsuarioInput usuarioInput);
	
	@ApiOperation("Exclui um usuário por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Usuário excluído"),
		@ApiResponse(code = 404, message = "Usuário não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID do usuário", example = "1", required = true)
			Long id);
	
}
