package org.locadora.Models;

import java.util.Objects;

public class Categoria {
    private int identificador;
    private String nome;

    public Categoria(){}

    public Categoria(int identificador, String nome) {
        this.identificador = identificador;
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Categoria categoria = (Categoria) object;
        return identificador == categoria.identificador && Objects.equals(nome, categoria.nome);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Nome: %s", identificador, nome);
    }
}