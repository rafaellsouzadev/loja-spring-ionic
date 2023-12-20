package com.rafael.lojaionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.lojaionic.domain.Categoria;
import com.rafael.lojaionic.repositories.CategoriaRepository;
import com.rafael.lojaionic.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "
				+ id + ", Tipo: " + Categoria.class.getName()));
	}

}
