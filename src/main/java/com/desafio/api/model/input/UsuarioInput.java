package com.desafio.api.model.input;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "John Doe")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "M")
	private String sexo;
	
	@ApiModelProperty(example = "contato@email.com")
	@Email
	private String email;
	
	@ApiModelProperty(example = "1980-09-26T00:00:00")
	@NotNull
	@DateTimeFormat
	private LocalDateTime dataNascimento;
	
	@ApiModelProperty(example = "Guanabara")
	private String naturalidade;
	
	@ApiModelProperty(example = "Brasileira")
	private String nacionalidade;
	
	@ApiModelProperty(example = "11111111111")
	@NotBlank
	@CPF
	private String cpf;

}
