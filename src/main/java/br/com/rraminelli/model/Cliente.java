package br.com.rraminelli.model;

import java.time.LocalDate;

public class Cliente {

    public enum Sexo {
        M,
        F
    }

    public Cliente(String nome, LocalDate dataNascimento, Sexo sexo) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.sexo = sexo;
    }

    private String nome;
    private LocalDate dataNascimento;
    private Sexo sexo;

    public boolean isFeminino() {
        return getSexo().equals(Sexo.F);
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pessoa{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", dataNascimento=").append(dataNascimento);
        sb.append('}');
        return sb.toString();
    }
}
