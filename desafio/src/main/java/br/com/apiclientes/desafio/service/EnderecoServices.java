package br.com.apiclientes.desafio.service;

import br.com.apiclientes.desafio.entity.Clientes;
import br.com.apiclientes.desafio.entity.Endereco;
import br.com.apiclientes.desafio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServices {


    @Autowired
    private EnderecoRepository enderecoRepository;

    public void deletarEndereco(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

}