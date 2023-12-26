package com.rafael.lojaionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.lojaionic.domain.Categoria;
import com.rafael.lojaionic.domain.Cidade;
import com.rafael.lojaionic.domain.Cliente;
import com.rafael.lojaionic.domain.Endereco;
import com.rafael.lojaionic.domain.Estado;
import com.rafael.lojaionic.domain.Produto;
import com.rafael.lojaionic.domain.enuns.TipoCliente;
import com.rafael.lojaionic.repositories.CategoriaRepository;
import com.rafael.lojaionic.repositories.CidadeRepository;
import com.rafael.lojaionic.repositories.ClienteRepository;
import com.rafael.lojaionic.repositories.EnderecoRepository;
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
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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
		
		Cliente cli1 = new Cliente(null, "Rafael de Souza Alves", "rmfashionmoda@gmail.com", "16734662063", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("85965702213", "85987665012"));
		
		Cliente cli2 = new Cliente(null, "Hinata Hyuga", "teste@gmail.com", "36712049075", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("85950227654"));
		
		Endereco end1 = new Endereco(null, "Rua 13 de Maio", "300", null, "Fátima", "60040531", cli1, cid1);
		
		Endereco end2 = new Endereco(null, "Rua 03", "234", null, "Parangaba", "60040531", cli2, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1));
		cli2.getEnderecos().addAll(Arrays.asList(end2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		
	}

}
