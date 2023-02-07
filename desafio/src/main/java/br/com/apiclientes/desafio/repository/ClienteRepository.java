package br.com.apiclientes.desafio.repository;

import br.com.apiclientes.desafio.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Clientes,Long> {
}