package br.com.apiclientes.desafio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos;

    public Clientes() {
    }

    public Clientes(String nome, Endereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        enderecos.add(endereco);
        endereco.isPrincipal();
        this.dataNascimento = dataNascimento;
    }

    public Clientes(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    public Clientes(Long id,String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes clientes)) return false;
        return Objects.equals(getId(), clientes.getId()) && Objects.equals(getNome(), clientes.getNome())
                && Objects.equals(getDataNascimento(), clientes.getDataNascimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDataNascimento());
    }


}