package org.locadora.Models;

public class Cliente {
    private String nome, cpf, cnh, telefone;

    public Cliente(String nome, String cpf, String cnh, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getCnh() {
        return cnh;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            return this.cpf.equals(cliente.getCpf());
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, CPF: %s, CNH: %s, Telefone: %s", nome, cpf, cnh, telefone);
    }

}

