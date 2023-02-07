package br.com.apiclientes.desafio.service;

import br.com.apiclientes.desafio.entity.Clientes;
import br.com.apiclientes.desafio.entity.Endereco;
import br.com.apiclientes.desafio.repository.ClienteRepository;
import br.com.apiclientes.desafio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private EnderecoServices enderecoService;


    public Clientes criarCliente(Clientes cliente) {
        return clienteRepository.save(cliente);
    }
    public Clientes atualizarCliente(Long id, Clientes clienteAtualizado) {
        Optional<Clientes> clienteBanco = clienteRepository.findById(id);
        if (clienteBanco.isPresent()) {
            Clientes clienteEncontrado = clienteBanco.get();
            clienteEncontrado.setNome(clienteAtualizado.getNome());
            clienteEncontrado.setDataNascimento(clienteAtualizado.getDataNascimento());
            clienteEncontrado.setEnderecos(clienteAtualizado.getEnderecos());
            return clienteRepository.save(clienteEncontrado);
        }
        return null;
    }


    public Clientes consultarCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);

    }

    public List<Clientes> listarClientes() {
        return clienteRepository.findAll();
    }

    public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
        Optional<Clientes> clienteBanco = clienteRepository.findById(pessoaId);
        if (clienteBanco.isPresent()) {
            Clientes cliente = clienteBanco.get();
            endereco.setCliente(cliente);
            return enderecoRepository.save(endereco);
        }
        return null;

    }


    public Endereco consultarEnderecoPrincipal(Long pessoaId) {
        Clientes cliente = clienteRepository.findById(pessoaId).orElse(null);
        if (cliente == null) {
            return null;
        }

        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.isPrincipal()) {
                return endereco;
            }
        }

        return null;
    }

    public Endereco definirEnderecoPrincipal(Long clienteId, Long enderecoId) {
        Clientes cliente= clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            return null;
        }

        for (Endereco endereco : cliente.getEnderecos()) {
            if (endereco.getId().equals(enderecoId)) {
                endereco.setPrincipal(true);
            } else {
                endereco.setPrincipal(false);
            }
        }

        clienteRepository.save(cliente);
        return consultarEnderecoPrincipal(clienteId);
    }


    public List<Endereco> consultarEnderecos(Long clienteId) {
        List<Endereco> listaGeral = enderecoRepository.findAll();
        List<Endereco> listaEnderecos = null;
        for (Endereco endereco:listaGeral) {
            if (endereco.getCliente().getId() == clienteId){
                listaEnderecos.add(endereco);
            }
        }
    return listaEnderecos;
    }

    public Endereco consultarEnderecoById(Long id){
        return enderecoRepository.findById(id).orElse(null);
    }

    public void deletarClienteById(Long id){
        clienteRepository.deleteById(id);
    }

    public void deletarCliente(Clientes cliente){
        clienteRepository.delete(cliente);
    }


    public void deletarEnderecoCliente(Long clienteId, Long enderecoId) {
        Clientes cliente = clienteRepository.findById(clienteId).orElse(null);
        Endereco endereco = enderecoRepository.findById(enderecoId).orElse(null);

        if (cliente.getEnderecos().contains(endereco)) {
            cliente.getEnderecos().remove(endereco);
            clienteRepository.save(cliente);
            enderecoRepository.delete(endereco);
        }


    }








}