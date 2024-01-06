package com.rafael.lojaionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.lojaionic.domain.Cliente;
import com.rafael.lojaionic.domain.dto.ClienteDTO;
import com.rafael.lojaionic.repositories.ClienteRepository;
import com.rafael.lojaionic.services.exceptions.DataIntegrityException;
import com.rafael.lojaionic.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
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
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
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
