package com.rafael.lojaionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.lojaionic.domain.Cidade;
import com.rafael.lojaionic.domain.Cliente;
import com.rafael.lojaionic.domain.Endereco;
import com.rafael.lojaionic.domain.dto.ClienteDTO;
import com.rafael.lojaionic.domain.dto.ClienteNewDTO;
import com.rafael.lojaionic.domain.enuns.TipoCliente;
import com.rafael.lojaionic.repositories.ClienteRepository;
import com.rafael.lojaionic.repositories.EnderecoRepository;
import com.rafael.lojaionic.services.exceptions.DataIntegrityException;
import com.rafael.lojaionic.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado ID: " + id
				+ ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		
		return cliente;
	}



	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possua pedidos");
		}
		
	}

	public List<ClienteDTO> findAll() {
		List<Cliente> result = clienteRepository.findAll();
		return result.stream().map(x -> new ClienteDTO(x)).toList();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cli = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(),
									clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()), pe.encode(clienteDTO.getSenha()));
		
		Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(),
									clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(),
									cli, cidade);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(clienteDTO.getTelefone1());
		
		if (clienteDTO.getTelefone2() != null) {
			cli.getTelefones().add(clienteDTO.getTelefone2());
		}
		if (clienteDTO.getTelefone3() != null) {
			cli.getTelefones().add(clienteDTO.getTelefone3());
		}
		
		return cli;
	}
	
	private void updateData(Cliente newCliente, Cliente cliente) {
		if (cliente.getNome() != null) {
			newCliente.setNome(cliente.getNome());
		}
		
		if (cliente.getEmail() != null) {
			newCliente.setEmail(cliente.getEmail());
		}
		
	}

}
