package br.com.apiclientes.desafio.entity;

import jakarta.persistence.*;

import java.util.Objects;



@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String logradouro;
    @Column(nullable = true)
    private String cep;
    @Column(nullable = true)
    private String numero;
    @Column(nullable = true)
    private String cidade;
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Clientes cliente;
    private boolean principal;


    public Endereco() {
    }

    public Endereco(String logradouro, String cep, String numero, String cidade, Clientes cliente, boolean principal) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.cliente = cliente;
        this.principal = principal;
    }



    public Endereco(String logradouro, String cep, String numero, String cidade) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }

    public Endereco(Long id, String logradouro, String numero, String cidade, String cep, Clientes cliente, boolean principal) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.cliente = cliente;
        this.principal = principal;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return getCliente().equals(endereco.getCliente()) && getId().equals(endereco.getId()) && getLogradouro().equals(endereco.getLogradouro()) && getCep().equals(endereco.getCep()) && getNumero().equals(endereco.getNumero()) && getCidade().equals(endereco.getCidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCliente(), getId(), getLogradouro(), getCep(), getNumero(), getCidade());
    }





}