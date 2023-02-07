package br.com.apiclientes.desafio.http.controller;

import br.com.apiclientes.desafio.entity.Clientes;
import br.com.apiclientes.desafio.entity.Endereco;
import br.com.apiclientes.desafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
//Optei por não criar o metodo de atualizar o endereço do cliente,
//ao inves disso fazer o processo de excluir o endereço e criar um novo

    @Autowired
    private  ClienteService clienteService;


    @PostMapping
    public ResponseEntity<Clientes> criarCliente(@RequestBody Clientes cliente) {
        return ResponseEntity.ok(clienteService.criarCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> atualizarCliente(@PathVariable("id") Long id, @RequestBody Clientes cliente) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clientes> obterCliente(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.consultarCliente(id));
    }

    @GetMapping
    public ResponseEntity<List<Clientes>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping("/{id}/endereco")
    public ResponseEntity<Endereco> criarEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        return ResponseEntity.ok(clienteService.criarEndereco(id, endereco));
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.consultarEnderecos(id));
    }

    @GetMapping("/{id}/endereco/principal")
    public ResponseEntity<Endereco> obterEnderecoPrincipal(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.consultarEnderecoPrincipal(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        Clientes cliente = clienteService.consultarCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        clienteService.deletarCliente(cliente);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{clienteId}/endereco/{enderecoId}")
    public ResponseEntity<Void> deletarEnderecoDoCliente(@PathVariable Long clienteId, @PathVariable Long enderecoId) {
        clienteService.deletarEnderecoCliente(clienteId, enderecoId);
        return ResponseEntity.noContent().build();
    }


}