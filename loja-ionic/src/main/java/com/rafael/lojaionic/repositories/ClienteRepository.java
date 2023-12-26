package com.rafael.lojaionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.lojaionic.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
