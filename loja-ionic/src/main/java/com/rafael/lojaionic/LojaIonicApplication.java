package com.rafael.lojaionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.lojaionic.domain.Categoria;
import com.rafael.lojaionic.domain.Cidade;
import com.rafael.lojaionic.domain.Estado;
import com.rafael.lojaionic.domain.Produto;
import com.rafael.lojaionic.repositories.CategoriaRepository;
import com.rafael.lojaionic.repositories.CidadeRepository;
import com.rafael.lojaionic.repositories.EstadoRepository;
import com.rafael.lojaionic.repositories.ProdutoRepository;

@SpringBootApplication
public class LojaIonicApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LojaIonicApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Ceará");
		
		estadoRepository.saveAll(Arrays.asList(est1));
		
		Cidade cid1 = new Cidade(null, "Fortaleza", est1);
		Cidade cid2 = new Cidade(null, "Maracanaú", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
		
		
	}

}
