package com.desafio.api.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "John Doe")
	private String nome;
	
	@ApiModelProperty(example = "M")
	private String sexo;
	
	@ApiModelProperty(example = "contato@email.com")
	private String email;
	
	@ApiModelProperty(example = "1980-09-26T00:00:00")
	private LocalDateTime dataNascimento;
	
	@ApiModelProperty(example = "Guanabara")
	private String naturalidade;
	
	@ApiModelProperty(example = "Brasileira")
	private String nacionalidade;
	
	@ApiModelProperty(example = "11111111111")
	private String cpf;

}
