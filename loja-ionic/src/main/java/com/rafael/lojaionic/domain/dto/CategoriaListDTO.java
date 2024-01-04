package com.rafael.lojaionic.domain.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.rafael.lojaionic.domain.Categoria;

public class CategoriaListDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaListDTO() {
	}
	
	public CategoriaListDTO(Categoria data) {
		BeanUtils.copyProperties(data, this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
