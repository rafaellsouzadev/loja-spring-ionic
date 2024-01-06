package com.rafael.lojaionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.lojaionic.domain.Categoria;
import com.rafael.lojaionic.domain.dto.CategoriaDTO;
import com.rafael.lojaionic.repositories.CategoriaRepository;
import com.rafael.lojaionic.services.exceptions.DataIntegrityException;
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
	
	public Categoria save(Categoria categoria) {
		categoria.setId(null);
		Categoria obj = categoriaRepository.save(categoria);
		return obj;
	}

	public Categoria update(Categoria categoria) {
		Categoria newCategoria = find(categoria.getId());
		updateData(newCategoria, categoria);
		return categoriaRepository.save(newCategoria);
	}



	public void delete(Integer id) {
		find(id);
		try {
		categoriaRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possua produtos");
		}
		
	}

	public List<CategoriaDTO> findAll() {
		List<Categoria> result = categoriaRepository.findAll();
		return result.stream().map(x -> new CategoriaDTO(x)).toList();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateData(Categoria newCategoria, Categoria categoria) {
		if (categoria.getNome() != null ) {
			newCategoria.setNome(categoria.getNome());
		}
		
	}

}
