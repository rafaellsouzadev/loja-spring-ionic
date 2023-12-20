package com.rafael.lojaionic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.lojaionic.domain.Produto;
import com.rafael.lojaionic.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto find(Integer id) {
		return null;
	}
}
