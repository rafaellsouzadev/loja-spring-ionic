package com.rafael.lojaionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.lojaionic.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
