package com.rafael.lojaionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.lojaionic.domain.Produto;
import com.rafael.lojaionic.repositories.ProdutoRepository;
import com.rafael.lojaionic.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() ->   new ObjectNotFoundException("Objeto não encontrado! id: " + id +
				", Tipo: " + Produto.class.getName()));
	}
}
