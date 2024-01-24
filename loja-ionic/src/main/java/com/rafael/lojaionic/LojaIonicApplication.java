package com.rafael.lojaionic;

import java.text.SimpleDateFormat;
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
import com.rafael.lojaionic.domain.ItemPedido;
import com.rafael.lojaionic.domain.Pagamento;
import com.rafael.lojaionic.domain.PagamentoComBoleto;
import com.rafael.lojaionic.domain.PagamentoComCartao;
import com.rafael.lojaionic.domain.Pedido;
import com.rafael.lojaionic.domain.Produto;
import com.rafael.lojaionic.domain.enuns.EstadoPagamento;
import com.rafael.lojaionic.domain.enuns.TipoCliente;
import com.rafael.lojaionic.repositories.CategoriaRepository;
import com.rafael.lojaionic.repositories.CidadeRepository;
import com.rafael.lojaionic.repositories.ClienteRepository;
import com.rafael.lojaionic.repositories.EnderecoRepository;
import com.rafael.lojaionic.repositories.EstadoRepository;
import com.rafael.lojaionic.repositories.ItemPedidoRepository;
import com.rafael.lojaionic.repositories.PagamentoRepository;
import com.rafael.lojaionic.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		/*------------------------------PRODUTO------------------------------------------------------------------------------------------------------*/
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de computador", 399.00);
		Produto p5 = new Produto(null, "Processador i7 12700k", 1249.00);
		Produto p6 = new Produto(null, "Processador Ryzen 5 5600G", 800.00);
		Produto p7 = new Produto(null, "TV Monitor 22 Polegada", 1200.00);
		Produto p8 = new Produto(null, "Grampeador grande 23/8-13", 149.00);
		Produto p9 = new Produto(null, "Papel Sulfite A4, 75g 300 Folhas", 20.70);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4, p8, p9));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat1));
		p8.getCategorias().addAll(Arrays.asList(cat2));
		p9.getCategorias().addAll(Arrays.asList(cat2));
		
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7,p8, p9));
		
		/*-----------------------------------ESTADOS-------------------------------------------------------------------------------------------------*/
		
		Estado est1 = new Estado(null, "Ceará");
		
		estadoRepository.saveAll(Arrays.asList(est1));
		
		/*----------------------------------CIDADES--------------------------------------------------------------------------------------------------*/
		
		Cidade cid1 = new Cidade(null, "Fortaleza", est1);
		Cidade cid2 = new Cidade(null, "Maracanaú", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
		
		/*----------------------------------CLIENTE E ENDEREÇOS-------------------------------------------------------------------------------------*/
		
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
		
		/*------------------------------------PEDIDO E PAGAMENTO------------------------------------------------------------------------------------*/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2023 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("25/12/2023 23:32"), cli2, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("28/12/2023 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		/*-------------------------------------ITENS DE PEDIDO-----------------------------------------------------------------------------------*/
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
